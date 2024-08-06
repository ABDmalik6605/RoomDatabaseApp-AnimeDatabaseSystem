package com.ffan.roomdatabaseapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.ffan.roomdatabaseapp.data.local.dao.AnimeDao
import com.ffan.roomdatabaseapp.data.local.dao.UserDao
import com.ffan.roomdatabaseapp.data.local.entities.Anime
import com.ffan.roomdatabaseapp.data.local.entities.User

@Database(entities = [User::class, Anime::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun animeDao(): AnimeDao
}