package com.example.movieapp.ui.home.overview

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.network.SmallMovieListSource
import com.example.movieapp.model.network.data.movie.ParentListMovie
import com.example.movieapp.model.network.data.movie.SmallMovieList
import com.example.movieapp.model.network.data.search.Genre
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val networkSource: SmallMovieListSource) :
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

    private val genresSource: Single<List<Genre>>
        get() = networkSource.fetchGenres("ru")

    private val moviesSource: Single<List<List<SmallMovieList>>>
    get() = networkSource.fetchSmallMovieList(categoryList, "ru")

    private val titleCategoryMap = hashMapOf(
        1 to ("Сейчас в кино" to "now_playing"),
        2 to ("Топ рейтинг" to "top_rated"),
        3 to ("Популярное" to "popular"),
        4 to ("Рекомендации" to "upcoming")
    )

    private fun fetchMoviesLists() {
        Log.d("ViewModel", "load data")

        val dis =
            moviesSource.zipWith(genresSource,
                BiFunction { movies: List<List<SmallMovieList>>, genres: List<Genre> ->
                    movies.mapIndexed { index, list ->
                        ParentListMovie(
                            titleCategoryMap[index]?.first ?: "",
                            titleCategoryMap[index]?.second ?: "",
                            list.withGenres(genres)
                        )
                    }
                }).subscribe({ parentMoviesList ->
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                _parentListMovie.value = parentMoviesList
            }, {
                if (parentListMovie.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
            )
        disposableBack.add(dis)
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


fun List<SmallMovieList>.withGenres(allGenres: List<Genre>): List<SmallMovieList> {
    return map { item ->
        item.apply {
            genres = genreIds.map { id ->
                allGenres.find { genre -> genre.id == id }?.name ?: ""
            }.filterNot(String::isNullOrEmpty)
        }
    }
}