package com.ffan.roomdatabaseapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var getDataButton: Button
    private lateinit var getUpdateButton: Button
    private lateinit var getInsertButton: Button
    private lateinit var getDeleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
        setListeners()
    }
    private fun initUI(){
        getDataButton = findViewById(R.id.Get)
        getUpdateButton = findViewById(R.id.Update)
        getInsertButton = findViewById(R.id.insert)
        getDeleteButton = findViewById(R.id.Delete)
    }
    private fun setListeners(){
        getDataButton.setOnClickListener {
            val intent = Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }
        getUpdateButton.setOnClickListener{
            val intent =Intent(this,UpdateActivity::class.java)
            startActivity(intent)
        }
        getInsertButton.setOnClickListener{
            val intent =Intent(this,InsertActivity::class.java)
            startActivity(intent)
        }
        getDeleteButton.setOnClickListener {
            val intent =Intent(this,DeleteActivity::class.java)
            startActivity(intent)
        }
    }
}
