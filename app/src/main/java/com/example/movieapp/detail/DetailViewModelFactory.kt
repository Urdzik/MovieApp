package com.example.movieapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.Movie

class DetailViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}