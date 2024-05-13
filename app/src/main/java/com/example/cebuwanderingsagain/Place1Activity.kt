package com.example.cebuwanderingsagain

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Place1Activity : AppCompatActivity() {

    private lateinit var messageListView: ListView
    private val messages = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place1)

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

        // Initialize views
        messageListView = findViewById(R.id.messageListView)
        val commentButton = findViewById<Button>(R.id.commentButton)

        // Set up adapter for ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messages)
        messageListView.adapter = adapter

        commentButton.setOnClickListener {
            showMessageDialog("Comment", -1) // -1 to indicate it's a new message
        }

        // Long click listener for deleting and updating messages
        messageListView.setOnItemLongClickListener { _, _, position, _ ->
            showOptionsDialog(position)
            true
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showMessageDialog(person: String, position: Int) {
        // Dialog setup
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_message, null)
        val messageEditText = dialogView.findViewById<EditText>(R.id.messageEditText) // Correct ID here

        // Pre-fill EditText if it's not a new message
        if (position != -1) {
            val existingMessage = messages[position]
            val message = existingMessage.substring(existingMessage.indexOf(": ") + 2)
            messageEditText.setText(message)
        }

        AlertDialog.Builder(this)
            .setTitle(if (position == -1) "New Message for $person" else "Edit Message")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val message = messageEditText.text.toString()
                if (message.isNotEmpty()) {
                    val formattedMessage = "$person: $message"
                    if (position == -1) {
                        // New message
                        messages.add(formattedMessage)
                    } else {
                        // Update existing message
                        messages[position] = formattedMessage
                    }
                    adapter.notifyDataSetChanged()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showOptionsDialog(position: Int) {
        val options = arrayOf("Update", "Delete")

        AlertDialog.Builder(this)
            .setTitle("Choose Action")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showMessageDialog("Edit Message", position)
                    1 -> showDeleteDialog(position)
                }
            }
            .show()
    }

    private fun showDeleteDialog(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Delete Message")
            .setMessage("Are you sure you want to delete this message?")
            .setPositiveButton("Delete") { dialog, _ ->
                messages.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
