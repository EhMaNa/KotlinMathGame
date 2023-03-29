package com.ehmana.kotlinmathgame

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ehmana.kotlinmathgame.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding

    override fun onBackPressed() {
        var alert = AlertDialog.Builder(this)
        alert.setTitle("Exit Game")
        alert.setMessage("Do you want to exit this game")
        alert.setCancelable(false)
        alert.setNegativeButton("EXIT") { dialog, i ->
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }
        alert.setPositiveButton("PLAY AGAIN") { dialog, i ->
        }
        alert.create().show()
    }
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

//        binding.buttonExit.setOnClickListener{
//            val intent = Intent(Intent.ACTION_MAIN)
//            intent.addCategory(Intent.CATEGORY_HOME)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)
//        }


    }


}