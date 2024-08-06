package com.ffan.roomdatabaseapp.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.adapter.ListRecyclerViewAdapter
import com.ffan.roomdatabaseapp.ui.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowOneActivity : AppCompatActivity() {

    private val animeViewModel: AnimeViewModel by viewModels()
    private lateinit var editTextAnimeName: EditText
    private lateinit var buttonGetAnime: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_one)

        // Initialize UI components
        editTextAnimeName = findViewById(R.id.editTextAnimeName)
        buttonGetAnime = findViewById(R.id.buttonGetAnime)
        recyclerView = findViewById(R.id.recyclerView)

        // Initialize RecyclerView and Adapter
        adapter = ListRecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Set up button click listener
        buttonGetAnime.setOnClickListener {
            val animeName = editTextAnimeName.text.toString()
            if (animeName.isNotEmpty()) {
                getAnime(animeName)
            } else {
                Toast.makeText(this, "Please enter an anime name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAnime(animeName: String) {
        animeViewModel.getAnimeByName(animeName).observe(this, Observer { animes ->
            if (animes != null && animes.isNotEmpty()) {
                adapter.setAnimes(animes)
            } else {
                Toast.makeText(this, "No anime found", Toast.LENGTH_SHORT).show()
                adapter.setAnimes(emptyList())
            }
        })
    }
}
