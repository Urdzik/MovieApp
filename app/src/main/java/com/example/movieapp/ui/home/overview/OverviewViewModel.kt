package com.example.movieapp.ui.home.overview

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.network.SmallMovieListSource
import com.example.movieapp.model.network.data.movie.ParentListMovie
import com.example.movieapp.model.network.data.movie.SmallMovieList
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class OverviewViewModel @Inject constructor(private val networkSource: SmallMovieListSource) : ViewModel() {

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



     fun fetchMoviesLists() {
        Log.d("ViewModel", "load data")
         val mListMovie = ArrayList<ParentListMovie>()
        var i = 1
        networkSource.fetchSmallMovieList(categoryList, "26f381d6ab8dd659b22d983cab9aa255", "ru")
            .subscribe({
                it.forEach {
                    when (i) {
                        1 -> {
                           mListMovie.add(
                               ParentListMovie(
                                   "Upcoming",
                                   "upcoming",
                                   it
                               )
                           )

                            i++
                        }
                        2 -> {  mListMovie.add(
                            ParentListMovie(
                                "Топ рейтинг",
                                "top_rated",
                                it
                            )
                        )


                            i++
                        }
                        3 -> { mListMovie.add(
                            ParentListMovie(
                                "Популярное",
                                "popular",
                                it
                            )
                        )


                            i++
                        }
                        4 -> { mListMovie.add(
                            ParentListMovie(
                                "Сейчас в кино",
                                "now_playing",
                                it
                            )
                        )

                        }
                    } }
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            _parentListMovie.value = mListMovie
            },{
                if (parentListMovie.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true}
            }
    )
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