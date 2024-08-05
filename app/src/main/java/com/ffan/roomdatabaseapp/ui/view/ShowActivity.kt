package com.ffan.roomdatabaseapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ffan.roomdatabaseapp.R

class ShowActivity : AppCompatActivity() {

    private lateinit var getAllButton: Button
    private lateinit var getOneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_choice)

        // Initialize buttons
        getAllButton = findViewById(R.id.GetAll)
        getOneButton = findViewById(R.id.GetOne)

        // Set click listeners
        getAllButton.setOnClickListener {
            // Navigate to ShowAll activity
            val intent = Intent(this, ShowAllActivity::class.java)
            startActivity(intent)
        }

        getOneButton.setOnClickListener {
            // Navigate to UpdateUserActivity
            val intent = Intent(this, ShowOneActivity::class.java)
            startActivity(intent)
        }
    }
}
