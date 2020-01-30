package com.example.movieapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.Movie

class DetailViewModel(movieProperty: Movie) : ViewModel() {

    //LiveData object of movie
    private val _selectProperty = MutableLiveData<Movie>()
    val selectProperty: LiveData<Movie>
        get() = _selectProperty

    init {
        _selectProperty.value = movieProperty
    }
}
