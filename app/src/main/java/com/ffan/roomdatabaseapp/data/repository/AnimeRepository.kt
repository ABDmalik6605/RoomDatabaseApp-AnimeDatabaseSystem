package com.ffan.roomdatabaseapp.data.repository

import com.ffan.roomdatabaseapp.data.local.dao.AnimeDao
import com.ffan.roomdatabaseapp.data.local.entities.Anime


class AnimeRepository(private val AnimeDao: AnimeDao) {
    suspend fun insertAnime(anime: Anime) {
        AnimeDao.insertAnime(anime)
    }
    suspend fun updateAnime(anime: Anime) {
        AnimeDao.updateAnime(anime)
    }
    suspend fun getAllAnimes(): List<Anime> {
        return AnimeDao.getAllAnimes()
    }
    suspend fun getAnimeByName(name: String): List <Anime> {
        return AnimeDao.getAnimesByName(name)
    }
}