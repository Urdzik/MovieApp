package com.example.movieapp.ui.overview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.data.NetworkMovie
import com.example.movieapp.model.network.NetworkSource
import com.example.movieapp.model.network.SmallNetworkSource
import com.example.movieapp.model.network.data.Small
import kotlinx.coroutines.launch
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val networkSource: SmallNetworkSource) : ViewModel() {

    var language: String = "ru"

    //LiveData object of movie
    private val _navigateToSelectProperty = MutableLiveData<Small>()
    val navigateToSelectProperty: LiveData<Small>
        get() = _navigateToSelectProperty

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData for show internet error
    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    private var _playList = MutableLiveData<List<Small>>()
    val playList: LiveData<List<Small>>
        get() = _playList

    val errorClickListener = View.OnClickListener { getMovieList(1, language) }


     fun getMovieList(i :Int, language: String) {
        viewModelScope.launch {
            try {
               _playList.value = networkSource.smallRetrieveData("top_rated" ,"26f381d6ab8dd659b22d983cab9aa255", language, i)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (e: Exception) {
                if (playList.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }



    fun displayPropertyDetails(movie: Small) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectProperty.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}