package com.example.cebuwanderingsagain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val spinner: Spinner = findViewById(R.id.spinner)

        // Create a list of items for the Spinner
        val items = listOf("CHOOSE","RESTAURANT", "MOUNTAIN", "BEACHES", "FOOD PARK")

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spinner.adapter = adapter

        // Set an item selected listener for the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                // Get the selected item
                val selectedItem = parent.getItemAtPosition(position).toString()

                // Navigate to the appropriate activity based on the selected item
                when (selectedItem) {
                    "RESTAURANT" -> navigateToRestaurantPage()
                    // Add cases for other options here
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    private fun navigateToRestaurantPage() {
        val intent = Intent(this, RestaurantPageActivity::class.java)
        startActivity(intent)
    }
}
