package com.example.cebuwanderingsagain

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val spinner: Spinner = findViewById(R.id.spinner)
        val items = listOf("CHOOSE", "RESTAURANT", "MOUNTAIN", "BEACHES", "FOOD PARK")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "RESTAURANT" -> showFragment(RestaurantFragment.newInstance("param1", "param2"))
                    "MOUNTAIN" -> showFragment(MountainFragment.newInstance("param1", "param2"))
                    // Add other cases if needed
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
