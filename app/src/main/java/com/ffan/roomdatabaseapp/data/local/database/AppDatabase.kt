package com.ffan.roomdatabaseapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ffan.roomdatabaseapp.data.local.dao.UserDao
import com.ffan.roomdatabaseapp.data.local.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    // ON UPDATE,,,, WHAT TO DO WHEN SCHEMA IS CHANGE
}