package com.example.movieapp.ui.list

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.NetworkSource
import com.example.movieapp.model.network.data.ListMovie
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(private  val networkSource: NetworkSource): ViewModel(){
    //LiveData object of movie

    private val _navigateToSelectProperty = MutableLiveData<ListMovie>()
    val navigateToSelectProperty: LiveData<ListMovie>
        get() = _navigateToSelectProperty

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData for show internet error
    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _playList = MutableLiveData<List<ListMovie>>()
    val playList: LiveData<List<ListMovie>>
        get() = _playList


     fun getMovieList(category: String, page: Int) {
        viewModelScope.launch {
            try {
                _playList.value = networkSource.retrieveData(category, "26f381d6ab8dd659b22d983cab9aa255", "ru", page)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (e: Exception) {
                if (playList.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

    fun displayPropertyDetails(movie: ListMovie) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectProperty.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }




}
