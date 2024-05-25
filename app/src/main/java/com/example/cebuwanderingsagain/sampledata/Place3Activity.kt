package com.example.cebuwanderingsagain.sampledata

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cebuwanderingsagain.HomePageActivity
import com.example.cebuwanderingsagain.ProfileActivity
import com.example.cebuwanderingsagain.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Place3Activity : AppCompatActivity() {

    private lateinit var messageListView: ListView
    private val messages = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance().reference.child("comments_place3")
    private val usersDatabase = FirebaseDatabase.getInstance().reference.child("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place3)

        auth = FirebaseAuth.getInstance()
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
            showMessageDialog(-1) // -1 to indicate it's a new message
        }

        // Long click listener for deleting and updating messages
        messageListView.setOnItemLongClickListener { _, _, position, _ ->
            showOptionsDialog(position)
            true
        }

        // Load comments from Firebase
        loadComments()
    }

    private fun loadComments() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messages.clear()
                for (data in snapshot.children) {
                    val comment = data.getValue(Comment::class.java)
                    if (comment != null) {
                        messages.add("${comment.userName}: ${comment.message}")
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }

    @SuppressLint("MissingInflatedId")
    private fun showMessageDialog(position: Int) {
        val currentUser = auth.currentUser ?: return

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_message, null)
        val messageEditText = dialogView.findViewById<EditText>(R.id.messageEditText)

        var existingMessage: String? = null
        if (position != -1) {
            existingMessage = messages[position].substring(messages[position].indexOf(": ") + 2)
            messageEditText.setText(existingMessage)
        }

        usersDatabase.child(currentUser.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val username = dataSnapshot.child("username").getValue(String::class.java) ?: "Anonymous"

                AlertDialog.Builder(this@Place3Activity)
                    .setTitle(if (position == -1) "New Message" else "Edit Message")
                    .setView(dialogView)
                    .setPositiveButton("Save") { dialog, _ ->
                        val message = messageEditText.text.toString()
                        if (message.isNotEmpty()) {
                            val comment = Comment(
                                userId = currentUser.uid,
                                userName = username,
                                message = message
                            )
                            if (position == -1) {
                                // New message
                                database.push().setValue(comment)
                            } else {
                                // Update existing message
                                val key = database.push().key // Generate a key for the message
                                if (key != null) {
                                    database.child(key).setValue(comment)
                                }
                            }
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })
    }

    private fun showOptionsDialog(position: Int) {
        val options = arrayOf("Update", "Delete")

        AlertDialog.Builder(this)
            .setTitle("Choose Action")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showMessageDialog(position)
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
                val comment = messages[position]
                database.child(comment).removeValue()
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
