package com.example.movieapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.database.DatabaseMovie

class DetailViewModelFactory(private val movie: DatabaseMovie) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}