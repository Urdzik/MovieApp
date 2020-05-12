package com.example.movieapp.ui.home.overview

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.SmallMovieListSource
import com.example.movieapp.model.network.data.SmallMovieList
import kotlinx.coroutines.launch
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val networkSource: SmallMovieListSource) : ViewModel() {

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

    //LiveData of Top Rated movies
    private var _topRatedPlayList = MutableLiveData<List<SmallMovieList>>()
    val topRatedPlayList: LiveData<List<SmallMovieList>>
        get() = _topRatedPlayList

    //LiveData of Popular movies
    private var _popularPlayList = MutableLiveData<List<SmallMovieList>>()
    val popularPlayList: LiveData<List<SmallMovieList>>
        get() = _popularPlayList

    //LiveData of now playing movies
    private var _nowPlayingPlayList = MutableLiveData<List<SmallMovieList>>()
    val nowPlayingPlayList: LiveData<List<SmallMovieList>>
        get() = _nowPlayingPlayList

    //LiveData of recommended movies
    private var _recViewingPlayList = MutableLiveData<List<SmallMovieList>>()
    val recViewingPlayList: LiveData<List<SmallMovieList>>
        get() = _recViewingPlayList

    val errorClickListener = View.OnClickListener { fetchMoviesLists() }

    init {
        Log.d("ViewModel", "init view model")
        fetchMoviesLists()
    }


    private fun fetchMoviesLists() {
        Log.d("ViewModel", "load data")
        viewModelScope.launch {
            try {
                _recViewingPlayList.value = networkSource.fetchSmallMovieList("upcoming", "26f381d6ab8dd659b22d983cab9aa255", "ru")
                _topRatedPlayList.value = networkSource.fetchSmallMovieList("top_rated", "26f381d6ab8dd659b22d983cab9aa255", "ru")
                _popularPlayList.value = networkSource.fetchSmallMovieList("popular", "26f381d6ab8dd659b22d983cab9aa255", "ru")
                _nowPlayingPlayList.value = networkSource.fetchSmallMovieList("now_playing", "26f381d6ab8dd659b22d983cab9aa255", "ru")

                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (e: Exception) {
                if (topRatedPlayList.value.isNullOrEmpty() || recViewingPlayList.value.isNullOrEmpty() || popularPlayList.value.isNullOrEmpty() || nowPlayingPlayList.value.isNullOrEmpty()) {
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