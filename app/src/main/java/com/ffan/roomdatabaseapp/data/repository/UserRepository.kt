package com.ffan.roomdatabaseapp.data.repository

import com.ffan.roomdatabaseapp.data.local.dao.UserDao
import com.ffan.roomdatabaseapp.data.local.entities.User


class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }
    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
    suspend fun getUserByName(name: String): User? {
        return userDao.getUserByName(name)
    }
}