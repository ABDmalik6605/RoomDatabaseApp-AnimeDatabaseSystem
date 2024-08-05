package com.ffan.roomdatabaseapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ffan.roomdatabaseapp.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(private val studentRepository: StudentRepository): ViewModel() {

}