package com.ffan.roomdatabaseapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ffan.roomdatabaseapp.data.local.entities.User
import com.ffan.roomdatabaseapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers: LiveData<List<User>> get() = _allUsers

    init {
        fetchAllUsers()
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
            fetchAllUsers() // Refresh the list of users after insertion
        }
    }

    private fun fetchAllUsers() {
        viewModelScope.launch {
            val users = userRepository.getAllUsers()
            _allUsers.postValue(users)
        }
    }
    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
            fetchAllUsers() // Refresh the list of users after updating
        }
    }
}
