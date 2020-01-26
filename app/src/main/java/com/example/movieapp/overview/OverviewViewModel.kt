package com.example.movieapp.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.getDatabase
import com.example.movieapp.domian.Movie
import com.example.movieapp.network.MovieProperty
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

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _property = MutableLiveData<List<Movie>>()
    val property: LiveData<List<Movie>>
        get() = _property

    private val _navigateToSelectProperty = MutableLiveData<Movie>()
    val navigateToSelectProperty: LiveData<Movie>
        get() = _navigateToSelectProperty


    private val moviesRepository = MoviesRepository(getDatabase(application))
    val playList = moviesRepository.movies

    init {
        getMovieListProperty()
    }


    private fun getMovieListProperty() {
        coroutineScope.launch {
            try {
                _status.value = MovieApiStatus.LOADING
                moviesRepository.refereshMovie()
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                if (playList.value!!.isEmpty()){
                    _status.value = MovieApiStatus.ERROR
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
