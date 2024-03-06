package com.example.cebuwanderingsagain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val loginButton = findViewById<Button>(R.id.restaurant)
        loginButton.setOnClickListener{
            val explicitIntent = Intent(this, RestaurantPageActivity::class.java)
            startActivity(explicitIntent)
        }

    }
}