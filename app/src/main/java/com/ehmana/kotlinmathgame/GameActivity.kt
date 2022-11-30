package com.ehmana.kotlinmathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ehmana.kotlinmathgame.databinding.ActivityGameBinding
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    lateinit var binding : ActivityGameBinding
    var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOk.setOnClickListener {
            if (binding.answer.text.toString().isNotEmpty()){

            } else {
                Toast.makeText(applicationContext,
                    "Please write an answer or click the next button", Toast.LENGTH_LONG).show()
            }
        }





    }

    fun getQuestion() {
        val number1 = Random.nextInt(10, 500)
        val number2 = Random.nextInt(10, 500)
        binding.questionText.text = "$number1 + $number2"
    }
}