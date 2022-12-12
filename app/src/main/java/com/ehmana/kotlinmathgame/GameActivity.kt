package com.ehmana.kotlinmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.ehmana.kotlinmathgame.databinding.ActivityGameBinding
import java.util.*
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGameBinding
    var correctAnswer = 0
    var userScore = 0
    var userLife = 5
    lateinit var timer : CountDownTimer
    var startTime : Long = 60000
    var timeLeft : Long = startTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nav = intent.getStringExtra("nav")
        navBar(nav!!)
        getQuestion(nav)

        binding.buttonOk.setOnClickListener {
            if (binding.answer.text.toString().isNotEmpty()){
                pauseTime()
                if (binding.answer.text.toString().toInt() == correctAnswer) {
                    userScore += 10
                    binding.questionText.setText(R.string.success)
                    binding.score.text = userScore.toString()
                    binding.buttonOk.isClickable = false
                } else {
                    userLife--
                    binding.questionText.setText(R.string.fail)
                    binding.life.text = userLife.toString()
                    binding.buttonOk.isClickable = false
                }

            } else {
                Toast.makeText(applicationContext,
                    "Please write an answer or click the next button", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonNxt.setOnClickListener {
            pauseTime()
            resetTime()

            binding.answer.setText("")
            binding.buttonOk.isClickable = true

            if (userLife != 0){
                getQuestion(nav)
            } else {
                Toast.makeText(applicationContext,"Sorry Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()

            }
        }
    }

    fun getQuestion(nav: String) {
        when(nav) {
               "add" -> {
                   val number1 = Random.nextInt(10, 500)
                   val number2 = Random.nextInt(10, 500)
                   binding.questionText.text = "$number1 + $number2"
                   correctAnswer = number1 + number2
                   time()
               }
            "multi" -> {
                val number1 = Random.nextInt(10, 500)
                val number2 = Random.nextInt(10, 500)
                binding.questionText.text = "$number1 x $number2"
                correctAnswer = number1 * number2
                time()
            }
            "sub" -> {
                val number1 = Random.nextInt(500, 1000)
                val number2 = Random.nextInt(100, 500)
                binding.questionText.text = "$number1 - $number2"
                correctAnswer = number1 - number2
                time()
            }
        }
    }

    fun time () {
        timer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(untilfinished: Long) {
                timeLeft = untilfinished
                updateText()

            }

            override fun onFinish() {

                pauseTime()
                resetTime()
                updateText()


                userLife--
                binding.questionText.setText(R.string.timeUp)
                binding.life.text = userLife.toString()
            }

        }.start()
    }
    fun updateText() {
        val remainingTime = (timeLeft / 1000).toInt()
        binding.time.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }
    fun pauseTime () {
        timer.cancel()
    }
    fun resetTime () {
        timeLeft = startTime
        updateText()
    }
    fun navBar(nav: String) {
        when (nav) {
            "add" -> supportActionBar!!.setTitle(R.string.buttonAdd)
            "sub" -> supportActionBar!!.setTitle( R.string.buttonSub)
            "multi" -> supportActionBar!!.setTitle(R.string.buttonMulti)


        }
    }
}