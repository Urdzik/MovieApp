package com.example.movieapp.ui.home.overview

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.MovieListSource
import com.example.movieapp.model.network.data.movie.ParentListMovie
import com.example.movieapp.model.network.data.movie.SmallMovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val networkSource: MovieListSource) :
    ViewModel() {

    private var disposableBack = CompositeDisposable()
    private val categoryList = listOf("upcoming", "top_rated", "popular", "now_playing")

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
    private var _parentListMovie = MutableLiveData<List<ParentListMovie>>()
    val parentListMovie: LiveData<List<ParentListMovie>>
        get() = _parentListMovie


    val errorClickListener = View.OnClickListener { fetchMoviesLists() }

    init {
        println("initialization viewModel")
        fetchMoviesLists()
    }


    private fun fetchMoviesLists() {
        Log.d("ViewModel", "load data")

        val titleCategoryMap = hashMapOf(
            1 to ("Сейчас в кино" to "now_playing"),
            2 to ("Топ рейтинг" to "top_rated"),
            3 to ("Популярное" to "popular"),
            4 to ("Рекомендации" to "upcoming")
        )

        viewModelScope.launch {
            networkSource.fetchSmallMovieList(
                categoryList,
                "26f381d6ab8dd659b22d983cab9aa255",
                "ru"
            )
                .collect { collectionList ->
                    val mListMovie = collectionList.mapIndexed { index, list ->
                        ParentListMovie(
                            titleCategoryMap[index]?.first ?: "",
                            titleCategoryMap[index]?.second ?: "",
                            list
                        )
                    }
                    _eventNetworkError.postValue(false)
                    _isNetworkErrorShown.postValue(false)
                    _parentListMovie.postValue(mListMovie)
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

    override fun onCleared() {
        super.onCleared()
        disposableBack.dispose()
    }
}