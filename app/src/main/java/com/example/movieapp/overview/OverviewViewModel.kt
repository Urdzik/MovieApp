package com.example.movieapp.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.getDatabase
import com.example.movieapp.domian.Movie
import com.example.movieapp.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MovieApiStatus {
    LOADING, DONE, ERROR
}

class OverviewViewModel(application: Application) : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToSelectProperty = MutableLiveData<Movie>()
    val navigateToSelectProperty: LiveData<Movie>
        get() = _navigateToSelectProperty

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    private val moviesRepository = MoviesRepository(getDatabase(application))
    val playList = moviesRepository.movies

    init {
        getMovieListProperty()
    }


    private fun getMovieListProperty() {
        coroutineScope.launch {
            try {
                moviesRepository.refreshMovie()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                if (playList.value!!.isEmpty()) {
                    _eventNetworkError.value = true
                }

            }
        }
    }

    fun displayPropertyDetails(movie: Movie) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsComlited() {
        _navigateToSelectProperty.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
