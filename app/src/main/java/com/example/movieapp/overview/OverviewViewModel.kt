package com.example.movieapp.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.DatabaseMovie
import com.example.movieapp.database.getDatabase
import com.example.movieapp.repository.MoviesRepository
import kotlinx.coroutines.launch

class OverviewViewModel(application: Application) : ViewModel() {

    //LiveData object of movie
    private val _navigateToSelectProperty = MutableLiveData<DatabaseMovie>()
    val navigateToSelectProperty: LiveData<DatabaseMovie>
        get() = _navigateToSelectProperty

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData for show internet error
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private val moviesRepository = MoviesRepository(getDatabase(application))
    val playList = moviesRepository.movies

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            try {
                moviesRepository.refreshMovie()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                if (playList.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

    fun displayPropertyDetails(movie: DatabaseMovie) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectProperty.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}