package com.ehmana.kotlinmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.ehmana.kotlinmathgame.databinding.ActivityGameBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGameBinding
    var correctAnswer : Any = 0
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
                    getString(R.string.info), Toast.LENGTH_LONG).show()
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
                Toast.makeText(applicationContext,getString(R.string.over), Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()

            }
        }
    }

    private fun getQuestion(nav: String) {
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
            "div" -> {
                val number1 = Random.nextInt(10, 100)
                val number2 = Random.nextInt(5, 50)
                binding.questionText.text = "$number1 ÷ $number2"
                val decimalFormat = DecimalFormat("#.##")
                decimalFormat.roundingMode = RoundingMode.DOWN
                correctAnswer = decimalFormat.format(number1 / number2)
                print(correctAnswer)
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
    private fun navBar(nav: String) {
        when (nav) {
            "add" -> supportActionBar!!.setTitle(R.string.buttonAdd)
            "sub" -> supportActionBar!!.setTitle( R.string.buttonSub)
            "multi" -> supportActionBar!!.setTitle(R.string.buttonMulti)
            "div" -> supportActionBar!!.setTitle(R.string.buttonDiv)


        }
    }
}