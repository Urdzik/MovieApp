package com.example.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.MovieInfoSource
import com.example.movieapp.model.network.data.MovieInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private  val movieInfoSource: MovieInfoSource) : ViewModel() {

    //LiveData object of movie
    private val _selectProperty = MutableLiveData<MovieInfo>()
    val selectProperty: LiveData<MovieInfo>
        get() = _selectProperty

     fun getSelectMovie(id: Int) {
        viewModelScope.launch {
            _selectProperty.value = movieInfoSource.retrieveInfoData(id)
        }
    }
}