package com.example.cebuwanderingsagain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cebuwanderingsagain.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class RestaurantPageActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_page)

        bottomNavigationView = findViewById(R.id.bottomNavigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home ->{
                    startActivity(Intent(this, HomePageActivity::class.java))
                    true
                }
                R.id.bottom_profile ->{
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
            else -> false
            }
        }
    }

}
