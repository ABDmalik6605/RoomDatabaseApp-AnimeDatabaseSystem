package com.ffan.roomdatabaseapp.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.adapter.ListRecyclerViewAdapter
import com.ffan.roomdatabaseapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowOneActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var editTextUserName: EditText
    private lateinit var buttonGetUser: Button
    private lateinit var textViewUserDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_one)

        // Initialize UI components
        editTextUserName = findViewById(R.id.editTextUserName)
        buttonGetUser = findViewById(R.id.buttonGetUser)
        textViewUserDetails = findViewById(R.id.textViewUserDetails)

        // Set up button click listener
        buttonGetUser.setOnClickListener {
            val userName = editTextUserName.text.toString()
            if (userName.isNotEmpty()) {
                getUser(userName)
            } else {
                Toast.makeText(this, "Please enter a user name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUser(userName: String) {
        userViewModel.getUserByName(userName).observe(this, Observer { user ->
            if (user != null) {
                val userDetails = "Name: ${user.name}\nAge: ${user.age}"
                textViewUserDetails.text = userDetails
            } else {
                textViewUserDetails.text = "User not found"
            }
        })
    }
}
