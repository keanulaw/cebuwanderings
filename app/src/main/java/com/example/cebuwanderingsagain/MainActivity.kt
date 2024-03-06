package com.example.cebuwanderingsagain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener{
            val explicitIntent = Intent(this, LoginActivity::class.java)
            startActivity(explicitIntent)
        }
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener{
            val explicitIntent = Intent(this, RegisterActivity::class.java)
            startActivity(explicitIntent)
        }
    }
}


