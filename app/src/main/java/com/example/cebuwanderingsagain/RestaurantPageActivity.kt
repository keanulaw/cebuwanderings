package com.example.cebuwanderingsagain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cebuwanderingsagain.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.ListView
import android.widget.Toast

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


        val listView: ListView = findViewById(R.id.listView)

        Places.addCustomer(Place("Shannon Keanu A. Yase",123, R.drawable.one))
        Places.addCustomer(Place("Edriane Amistoso", 321, R.drawable.two))
        Places.addCustomer(Place("Joey Velasco", 234, R.drawable.three))

        val adapter = MyAdapter(this, Places.getCustomer())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    val explicitIntent = Intent(this, Place1Activity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(this@RestaurantPageActivity, "Place One", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    val explicitIntent = Intent(this, Place2Activity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(this@RestaurantPageActivity, "Place Two", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    val explicitIntent = Intent(this, Place3Activity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(this@RestaurantPageActivity, "Place Three", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
