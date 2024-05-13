package com.example.cebuwanderingsagain

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cebuwanderingsagain.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerTextView = findViewById<TextView>(R.id.registerTextView)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                // Username or password is empty
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val savedUsername = sharedPreferences.getString("username", "")
                val savedPassword = sharedPreferences.getString("password", "")

                if (username == savedUsername && password == savedPassword) {
                    // Login successful
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                } else {
                    // Login failed
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Move this block outside of the loginButton.setOnClickListener block
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
