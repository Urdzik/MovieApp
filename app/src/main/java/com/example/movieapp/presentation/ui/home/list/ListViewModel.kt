package com.example.movieapp.presentation.ui.home.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.movie.ListMovie
import com.example.movieapp.domain.usecase.FetchMovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val fetchMovieList: FetchMovieList) : ViewModel() {

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

    //LiveData of list Movies
    private var _playList = MutableLiveData<List<ListMovie>>()
    val playList: LiveData<List<ListMovie>>
        get() = _playList


    fun errorClickListener(category: String) {
        getMovieList(category, 1)
    }

    fun getMovieList(category: String, page: Int) {
        viewModelScope.launch {
            val value = fetchMovieList.invoke(
                category,
                "ru",
                page
            )
            _playList.value = value
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
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