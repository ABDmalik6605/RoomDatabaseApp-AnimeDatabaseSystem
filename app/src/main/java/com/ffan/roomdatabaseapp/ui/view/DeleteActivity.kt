package com.ffan.roomdatabaseapp.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.ui.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteActivity : AppCompatActivity() {

    private val animeViewModel: AnimeViewModel by viewModels()
    private lateinit var etAnimeNameToDelete: EditText
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        etAnimeNameToDelete = findViewById(R.id.etAnimeNameToDelete)
        btnDelete = findViewById(R.id.btnDelete)

        btnDelete.setOnClickListener {
            val animeName = etAnimeNameToDelete.text.toString()

            if (animeName.isNotEmpty()) {
                deleteAnime(animeName)
            } else {
                Toast.makeText(this, "Please enter the anime name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteAnime(animeName: String) {
        animeViewModel.getAnimeByName(animeName).observe(this, Observer { anime ->
            if (anime != null) {
                animeViewModel.deleteAnime(anime)
                Toast.makeText(this, "Anime deleted", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Anime not found", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
