package com.example.cebuwanderingsagain

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton: Button = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Save username and password to shared preferences
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            // Optionally, you can also store other user information here

            // After registering, you can navigate back to the login page
            finish()
        }
    }
}
