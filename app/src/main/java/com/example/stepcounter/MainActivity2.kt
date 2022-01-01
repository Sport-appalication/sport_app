package com.example.stepcounter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val c1 = findViewById<Button>(R.id.c1)

        c1.setOnClickListener {
            val Intent2 = Intent( this,footballActivity::class.java)
            startActivity(Intent2)
        }

        val c2 = findViewById<Button>(R.id.c2)

        c2.setOnClickListener {
            val Intent3 = Intent( this,rugbyActivity::class.java)
            startActivity(Intent3)
        }

        val c3 = findViewById<Button>(R.id.c3)

        c3.setOnClickListener {
            val Intent4 = Intent( this,basketballActivity::class.java)
            startActivity(Intent4)
        }

        val c4 = findViewById<Button>(R.id.c4)

        c4.setOnClickListener {
            val Intent6 = Intent( this,runningActivity::class.java)
            startActivity(Intent6)
        }


    }



}