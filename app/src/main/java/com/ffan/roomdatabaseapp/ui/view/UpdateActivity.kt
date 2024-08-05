package com.ffan.roomdatabaseapp.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.data.local.entities.User
import com.ffan.roomdatabaseapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var etOldName: EditText
    private lateinit var etNewName: EditText
    private lateinit var etNewAge: EditText
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        etOldName = findViewById(R.id.etOldName)
        etNewName = findViewById(R.id.etNewName)
        etNewAge = findViewById(R.id.etNewAge)
        btnUpdate = findViewById(R.id.btnUpdate)

        btnUpdate.setOnClickListener {
            val oldName = etOldName.text.toString()
            val newName = etNewName.text.toString()
            val newAge = etNewAge.text.toString().toIntOrNull()

            if (oldName.isNotEmpty() && newName.isNotEmpty() && newAge != null) {
                updateUser(oldName, newName, newAge)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUser(oldName: String, newName: String, newAge: Int) {
        userViewModel.allUsers.observe(this, Observer { users ->
            val user = users.find { it.name == oldName }
            if (user != null) {
                val updatedUser = user.copy(name = newName, age = newAge)
                userViewModel.updateUser(updatedUser)
                Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
