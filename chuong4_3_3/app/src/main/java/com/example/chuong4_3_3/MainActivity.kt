package com.example.chuong4_3_3

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUsername = findViewById(R.id.edit_text_username)
        editTextPassword = findViewById(R.id.edit_text_password)
    }

    fun onLoginClicked(view: View) {
        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()

        if (username == "admin" && password == "123") {
            Toast.makeText(applicationContext, "Login success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}
