package com.ffan.roomdatabaseapp.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.data.local.entities.Anime
import com.ffan.roomdatabaseapp.ui.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateActivity : AppCompatActivity() {

    private val animeViewModel: AnimeViewModel by viewModels()
    private lateinit var etOldName: EditText
    private lateinit var etNewName: EditText
    private lateinit var etNewFavChar: EditText
    private lateinit var etNewGenre: EditText
    private lateinit var etNewRating: EditText
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        etOldName = findViewById(R.id.etOldName)
        etNewName = findViewById(R.id.etNewName)
        etNewFavChar = findViewById(R.id.etNewFavChar)
        etNewGenre = findViewById(R.id.etNewGenre)
        etNewRating = findViewById(R.id.etNewRating)
        btnUpdate = findViewById(R.id.btnUpdate)

        btnUpdate.setOnClickListener {
            val oldName = etOldName.text.toString()
            val newName = etNewName.text.toString()
            val newFavChar = etNewFavChar.text.toString()
            val newGenre = etNewGenre.text.toString()
            val newRating = etNewRating.text.toString().toFloatOrNull()

            if (oldName.isNotEmpty()) {
                updateAnime(oldName, newName, newFavChar, newGenre, newRating)
            } else {
                Toast.makeText(this, "Please enter the old name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateAnime(oldName: String, newName: String, newFavChar: String, newGenre: String, newRating: Float?) {
        animeViewModel.getAnimeByName(oldName).observe(this, Observer { anime ->
            if (anime != null) {
                val updatedAnime = anime.copy(
                    name = if (newName.isNotEmpty()) newName else anime.name,
                    favChar = if (newFavChar.isNotEmpty()) newFavChar else anime.favChar,
                    genre = if (newGenre.isNotEmpty()) newGenre else anime.genre,
                    rating = newRating ?: anime.rating
                )
                animeViewModel.updateAnime(updatedAnime)
                Toast.makeText(this, "Anime updated", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Anime not found", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
