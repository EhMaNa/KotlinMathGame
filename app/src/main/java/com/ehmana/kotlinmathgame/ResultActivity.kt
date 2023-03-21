package com.ehmana.kotlinmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ehmana.kotlinmathgame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val score = intent.getIntExtra("score", 0)


        binding.scoreText.text = "Your Score $score"
        binding.buttonPlayAgain.setOnClickListener {
            finish()
        }




    }


}