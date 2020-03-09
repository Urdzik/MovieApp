package com.example.movieapp.ui.overview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.SmallNetworkSource
import com.example.movieapp.model.network.data.SmallMovieList
import kotlinx.coroutines.launch
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val networkSource: SmallNetworkSource) : ViewModel() {


    //LiveData object of movie
    private val _navigateToSelectProperty = MutableLiveData<SmallMovieList>()
    val navigateToSelectProperty: LiveData<SmallMovieList>
        get() = _navigateToSelectProperty

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData for show internet error
    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    private var _playList = MutableLiveData<List<SmallMovieList>>()
    val playList: LiveData<List<SmallMovieList>>
        get() = _playList

    val errorClickListener = View.OnClickListener { getMovieList() }

    fun getMovieList() {
        viewModelScope.launch {
            try {
                _playList.value = networkSource.retrievePoster(
                    "top_rated",
                    "26f381d6ab8dd659b22d983cab9aa255",
                    "ru"
                )
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (e: Exception) {
                if (playList.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

    fun displayPropertyDetails(movie: SmallMovieList) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectProperty.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}