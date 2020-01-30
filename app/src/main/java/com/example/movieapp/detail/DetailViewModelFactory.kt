package com.example.movieapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.Movie

class DetailViewModelFactory(private val movieProperty: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movieProperty) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}