package com.example.cebuwanderingsagain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val signupUsernameEditText: EditText = findViewById(R.id.signupUsernameEditText)
        val signupEmailEditText: EditText = findViewById(R.id.signupEmailEditText)
        val signupPasswordEditText: EditText = findViewById(R.id.signupPasswordEditText)
        val confirmPasswordEditText: EditText = findViewById(R.id.confirmPasswordEditText)
        val signupButton: Button = findViewById(R.id.signupButton)

        signupButton.setOnClickListener {
            val username = signupUsernameEditText.text.toString()
            val email = signupEmailEditText.text.toString()
            val password = signupPasswordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {
                signUp(username, email, password)
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUp(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userId = user?.uid
                    val userMap = hashMapOf<String, Any>(
                        "username" to username,
                        "email" to email
                    )

                    if (userId != null) {
                        database.child("users").child(userId).setValue(userMap)
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this, "Sign Up Failed: ${dbTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                } else {
                    Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
