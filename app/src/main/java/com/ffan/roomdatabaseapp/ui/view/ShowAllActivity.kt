package com.ffan.roomdatabaseapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ffan.roomdatabaseapp.R
import com.ffan.roomdatabaseapp.adapter.ListRecyclerViewAdapter
import com.ffan.roomdatabaseapp.data.local.entities.Anime
import com.ffan.roomdatabaseapp.ui.viewmodel.AnimeViewModel
import com.ffan.roomdatabaseapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowAllActivity : AppCompatActivity() {

    private val animeViewModel: AnimeViewModel by viewModels()
    private lateinit var animeAdapter: ListRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all)

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        animeAdapter = ListRecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = animeAdapter

//        val anime = Anime(name = "Death Note", favChar = "Light Yagami", genre = "Suspense", rating = 5f)
//        animeViewModel.insertAnime(anime)

        // Observe LiveData
        animeViewModel.allAnimes.observe(this) { animes ->
            animes?.let {
                animeAdapter.setAnimes(it)
            }
        }
    }
}
