package com.example.movieapp.ui.home.overview

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.network.SmallMovieListSource
import com.example.movieapp.model.network.data.ParentListMovie
import com.example.movieapp.model.network.data.SmallMovie
import kotlinx.coroutines.launch
import com.example.movieapp.model.network.data.SmallMovieList
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val networkSource: SmallMovieListSource) : ViewModel() {

    private var disposableBack = CompositeDisposable()
    private val categoryList = listOf("upcoming", "top_rated", "popular", "now_playing")

    //LiveData object of movie
    private val _navigateToSelectProperty = MutableLiveData<SmallMovie>()
    val navigateToSelectProperty: LiveData<SmallMovie>
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
    private var _parentListMovie = MutableLiveData<List<ParentListMovie>>()
    val parentListMovie: LiveData<List<ParentListMovie>>
        get() = _parentListMovie


    val errorClickListener = View.OnClickListener { fetchMoviesLists() }

    init {
        println("initialization viewModel")
        fetchMoviesLists()
    }


    @SuppressLint("NewApi")
    private fun fetchMoviesLists() {
        Log.d("ViewModel", "load data")
        var i = 1
        networkSource.fetchSmallMovieList(categoryList, "26f381d6ab8dd659b22d983cab9aa255", "ru")
            .subscribe({
                it.forEach {
                    when (i) {
                        1 -> {
                            _recViewingPlayList.value = it
                            i++
                        }
                        2 -> {
                            _topRatedPlayList.value = it
                            i++
                        }
                        3 -> {
                            _popularPlayList.value = it
                            i++
                        }
                        4 -> {
                            _nowPlayingPlayList.value = it
                        }
                    } }
     fun fetchMoviesLists() {

        viewModelScope.launch {
            try {

                val topRated = networkSource.fetchSmallMovieList("top_rated", "ru")
                val popular = networkSource.fetchSmallMovieList("popular",  "ru")
                val nowPlaying = networkSource.fetchSmallMovieList("now_playing",  "ru")
                val upcoming = networkSource.fetchSmallMovieList("upcoming", "ru")

                _parentListMovie.value = listOf(
                    ParentListMovie("Upcoming","upcoming", upcoming),
                    ParentListMovie("Топ рейтинг","top_rated", topRated),
                    ParentListMovie("Популярное","popular", popular),
                    ParentListMovie("Сейчас в кино","now_playing", nowPlaying)
                )

                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false


            } catch (e: Exception) {
                if (parentListMovie.value.isNullOrEmpty()) {
            }, {
                if (topRatedPlayList.value.isNullOrEmpty() || recViewingPlayList.value.isNullOrEmpty() || popularPlayList.value.isNullOrEmpty() || nowPlayingPlayList.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }

            })

    }



    fun displayPropertyDetails(movie: SmallMovie) {
        _navigateToSelectProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectProperty.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    override fun onCleared() {
        super.onCleared()
        disposableBack.dispose()
    }
}