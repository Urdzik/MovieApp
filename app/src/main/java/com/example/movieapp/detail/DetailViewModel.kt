package com.example.movieapp.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.network.MovieProperty

class DetailViewModel(movieProperty: MovieProperty, application: Application) : ViewModel() {

    private val _selectProperty = MutableLiveData<MovieProperty>()
    val selectProperty: LiveData<MovieProperty>
        get() = _selectProperty

    init {
        _selectProperty.value = movieProperty
    }
}
