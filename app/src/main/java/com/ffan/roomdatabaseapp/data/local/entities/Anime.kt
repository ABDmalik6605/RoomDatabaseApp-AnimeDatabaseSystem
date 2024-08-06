package com.ffan.roomdatabaseapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animes")
data class Anime(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val favChar: String,
    val genre: String,
    val rating: Float
)