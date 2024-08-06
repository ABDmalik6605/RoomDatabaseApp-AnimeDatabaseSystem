package com.ffan.roomdatabaseapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ffan.roomdatabaseapp.data.local.entities.Anime
import com.ffan.roomdatabaseapp.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel(){
    private val _allAnimes = MutableLiveData<List<Anime>>()
    val allAnimes: LiveData<List<Anime>> get() = _allAnimes

    init {
        fetchAllAnimes()
    }

    fun insertAnime(anime: Anime) {
        viewModelScope.launch {
            animeRepository.insertAnime(anime)
            fetchAllAnimes() // Refresh the list of users after insertion
        }
    }

    private fun fetchAllAnimes() {
        viewModelScope.launch {
            val anime = animeRepository.getAllAnimes()
            _allAnimes.postValue(anime)
        }
    }
    fun updateAnime(anime: Anime) {
        viewModelScope.launch {
            animeRepository.updateAnime(anime)
            fetchAllAnimes() // Refresh the list of users after updating
        }
    }

    fun getAnimeByName(name: String): LiveData<List<Anime>> {
        val result = MutableLiveData<List<Anime>>()
        viewModelScope.launch {
            val animes = animeRepository.getAnimeByName(name)
            result.postValue(animes)
        }
        return result
    }

}