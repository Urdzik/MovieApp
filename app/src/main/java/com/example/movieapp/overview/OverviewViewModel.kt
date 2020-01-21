package com.example.movieapp.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.network.MovieApi
import com.example.movieapp.network.MovieProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MovieApiStatus {
    LOADING, DONE, ERROR
}

class OverviewViewModel : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _property = MutableLiveData<List<MovieProperty>>()
    val property: LiveData<List<MovieProperty>>
        get() = _property

    private val _navigateToSelectProperty = MutableLiveData<MovieProperty>()
    val navigateToSelectProperty: LiveData<MovieProperty>
        get() = _navigateToSelectProperty

    init {
        getMovieListProperty()
    }


    private fun getMovieListProperty() {
        coroutineScope.launch {
            val getPropertyDeferred = MovieApi.retrofitService.getPropertyAsync()
            try {
                _status.value = MovieApiStatus.LOADING
                val listProperty = getPropertyDeferred.await()
                _property.value = listProperty
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                _property.value = ArrayList()
                Log.i("Message", e.message)
            }
        }
    }

    fun displayPropertyDetails(movieProperty: MovieProperty){
        _navigateToSelectProperty.value = movieProperty
    }

    fun displayPropertyDetailsComlited(){
        _navigateToSelectProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
