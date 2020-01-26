package com.example.movieapp.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.domian.Movie
import com.example.movieapp.network.MovieProperty

class DetailViewModel(movieProperty: Movie, application: Application) : ViewModel() {

    private val _selectProperty = MutableLiveData<Movie>()
    val selectProperty: LiveData<Movie>
        get() = _selectProperty

    init {
        _selectProperty.value = movieProperty
    }
}
