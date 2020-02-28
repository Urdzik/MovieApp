package com.example.movieapp.ui.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.NetworkMovie
import com.example.movieapp.model.network.NetworkSource
import kotlinx.coroutines.launch
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val networkSource: NetworkSource) :
    ViewModel() {

    //LiveData object of movie
    private val _navigateToSelectProperty = MutableLiveData<NetworkMovie>()
    val navigateToSelectProperty: LiveData<NetworkMovie>
        get() = _navigateToSelectProperty


    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData for show internet error
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    private var _playList = MutableLiveData<List<NetworkMovie>>()
    val playList: LiveData<List<NetworkMovie>>
        get() = _playList


    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            try {
                _playList.value = networkSource.retrieveData()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                if (playList.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

    fun displayPropertyDetails(movie: NetworkMovie) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectProperty.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


}