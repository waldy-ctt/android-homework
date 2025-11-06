package com.example.chuong4_3_2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewCounter = findViewById<TextView>(R.id.textViewCounter)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)

        buttonPlus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                counter++
                textViewCounter.text = counter.toString()
            }
        })

        buttonMinus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                counter--
                textViewCounter.text = counter.toString()
            }
        })
    }
}
