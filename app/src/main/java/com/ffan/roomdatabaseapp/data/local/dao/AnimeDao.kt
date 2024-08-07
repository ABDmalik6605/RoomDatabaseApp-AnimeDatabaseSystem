package com.ffan.roomdatabaseapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ffan.roomdatabaseapp.data.local.entities.Anime

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: Anime)

    @Query("SELECT * FROM animes")
    suspend fun getAllAnimes(): List<Anime>

    @Update
    suspend fun updateAnime(anime: Anime)

    @Query("SELECT * FROM animes WHERE name LIKE '%' || :animeName || '%'")
    suspend fun getAnimesByName(animeName: String): List<Anime>

    @Query("SELECT * FROM animes WHERE name= :animeName LIMIT 1")
    suspend fun getAnimeByName(animeName: String): Anime?

    @Delete
    suspend fun deleteAnime(anime: Anime)
}