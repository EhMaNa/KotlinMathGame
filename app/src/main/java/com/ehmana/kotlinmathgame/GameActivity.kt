package com.ehmana.kotlinmathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ehmana.kotlinmathgame.databinding.ActivityGameBinding
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    lateinit var binding : ActivityGameBinding
    var correctAnswer = 0
    var userScore = 0
    var userLife = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getQuestion()

        binding.buttonOk.setOnClickListener {
            if (binding.answer.text.toString().isNotEmpty()){
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
            getQuestion()
            binding.answer.setText("")
            binding.buttonOk.isClickable = true
        }





    }

    fun getQuestion() {
        var number1 = Random.nextInt(10, 500)
        var number2 = Random.nextInt(10, 500)
        binding.questionText.text = "$number1 + $number2"
        correctAnswer = number1 + number2
    }
}