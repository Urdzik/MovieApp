package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.ListMovie
import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.Results
import com.example.movieapp.model.network.data.SmallMovieList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListSource @Inject constructor(private val api: MovieApi) {
    fun fetchMovieList(category: String, key: String, language: String, page: Int): Single<List<ListMovie>> {
        return api.getPropertyAsync(category, key, language, page)
            .map { it.networkMovie }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}

class SmallMovieListSource @Inject constructor(private val api: MovieApi) {
    suspend fun fetchSmallMovieList(
        category: String, key: String, language: String
    ): List<SmallMovieList> = withContext(Dispatchers.IO) {
        val playList = api.getListOfPosters(category, key, language)
        playList.smallMovieList
    }
}

class MovieDetailSource @Inject constructor(private val api: MovieApi) {
    suspend fun fetchDetailInformationOfMovie(id: Int): MovieInfo = withContext(Dispatchers.IO) {
        val infoOfMovie = api.getMovieByID(id)
        infoOfMovie
    }
}