package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        findViewById<TextInputLayout>(R.id.emailInput).error = "Wrong mail format"

        val inputLayout = findViewById<EditText>(R.id.emailInput)

        val registerNowText: TextView = findViewById(R.id.registerNowText)

        registerNowText.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
}
