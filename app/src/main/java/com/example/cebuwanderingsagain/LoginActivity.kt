package com.example.cebuwanderingsagain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val loginEmailEditText: EditText = findViewById(R.id.loginEmailEditText)
        val loginPasswordEditText: EditText = findViewById(R.id.loginPasswordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val registerTextView: TextView = findViewById(R.id.registerTextView)

        loginButton.setOnClickListener {
            val email = loginEmailEditText.text.toString()
            val password = loginPasswordEditText.text.toString()
            login(email, password)
        }

        registerTextView.setOnClickListener {
            // Navigate to SignupActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    // Navigate to HomePageActivity
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                    finish() // Finish LoginActivity so user can't navigate back to it
                } else {
                    Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
