package com.example.movieapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.DatabaseMovie

class DetailViewModel(movieProperty: DatabaseMovie) : ViewModel() {

    //LiveData object of movie
    private val _selectProperty = MutableLiveData<DatabaseMovie>()
    val selectProperty: LiveData<DatabaseMovie>
        get() = _selectProperty

    init {
        _selectProperty.value = movieProperty
    }
}