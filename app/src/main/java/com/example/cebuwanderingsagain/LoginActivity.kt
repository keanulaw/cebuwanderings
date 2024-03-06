package com.example.cebuwanderingsagain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cebuwanderingsagain.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding // Use the correct binding class

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var loginButton : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener(View.OnClickListener {
            if (binding.username.text.toString() == "user" && binding.password.text.toString() == "1234"){

                val loginButton = findViewById<Button>(R.id.loginButton)
                loginButton.setOnClickListener{
                    val explicitIntent = Intent(this, HomePageActivity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
