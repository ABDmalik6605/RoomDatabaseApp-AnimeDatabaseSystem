package com.ffan.roomdatabaseapp.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.data.local.entities.Anime
import com.ffan.roomdatabaseapp.ui.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertActivity : AppCompatActivity() {

    private val animeViewModel: AnimeViewModel by viewModels()
    private lateinit var etName: EditText
    private lateinit var etFavChar: EditText
    private lateinit var etGenre: EditText
    private lateinit var etRating: EditText
    private lateinit var btnInsert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        initUI()
    }

    private fun initUI() {
        etName = findViewById(R.id.etName)
        etFavChar = findViewById(R.id.etFavChar)
        etGenre = findViewById(R.id.etGenre)
        etRating = findViewById(R.id.etRating)
        btnInsert = findViewById(R.id.btnInsert)

        etRating.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            try {
                val input = (dest.toString() + source.toString()).toFloat()
                if (input in 0.0..5.0) {
                    null
                } else {
                    ""
                }
            } catch (e: NumberFormatException) {
                ""
            }
        })

        etRating.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().toFloatOrNull()
                if (input != null && (input < 0.0 || input > 5.0)) {
                    etRating.error = "Rating must be between 0 and 5"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnInsert.setOnClickListener {
            val name = etName.text.toString()
            val favChar = etFavChar.text.toString()
            val genre = etGenre.text.toString()
            val rating = etRating.text.toString().toFloatOrNull()

            if (name.isNotEmpty() && favChar.isNotEmpty() && genre.isNotEmpty() && rating != null) {
                insertAnime(name, favChar, genre, rating)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertAnime(name: String, favChar: String, genre: String, rating: Float) {
        val anime = Anime(name = name, favChar = favChar, genre = genre, rating = rating)
        animeViewModel.insertAnime(anime)
        Toast.makeText(this, "Anime inserted", Toast.LENGTH_SHORT).show()
        finish()
    }
}
