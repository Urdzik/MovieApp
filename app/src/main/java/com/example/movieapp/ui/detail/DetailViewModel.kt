package com.example.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.network.NetworkMovie
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    //LiveData object of movie
    private val _selectProperty = MutableLiveData<NetworkMovie>()
    val selectProperty: LiveData<NetworkMovie>
        get() = _selectProperty


    fun getSelectMovie(networkMovie: NetworkMovie) {
        _selectProperty.value = networkMovie
    }
}