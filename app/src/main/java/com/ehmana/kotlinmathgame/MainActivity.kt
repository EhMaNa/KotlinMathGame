package com.ehmana.kotlinmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ehmana.kotlinmathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this@MainActivity, GameActivity::class.java)

        binding.buttonAdd.setOnClickListener {
            intent.putExtra("nav", "add")
            startActivity(intent)
        }
        binding.buttonMulti.setOnClickListener {
            intent.putExtra("nav", "multi")
            startActivity(intent)
        }
        binding.buttonSub.setOnClickListener {
            intent.putExtra("nav", "sub")
            startActivity(intent)
        }

        binding.buttonDiv.setOnClickListener {
            intent.putExtra("nav", "div")
            startActivity(intent)
        }

    }
}