package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.ListMovie
import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.SmallMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListSource @Inject constructor(private val api: MovieApi) {
    suspend fun fetchMovieList(
        category: String, key: String, language: String, page: Int
    ): List<ListMovie> = withContext(Dispatchers.IO) {
        val playList = api.getPropertyAsync(category, key, language, page)
        playList.networkMovie
    }
}

class SmallMovieListSource @Inject constructor(private val api: MovieApi) {
    suspend fun fetchSmallMovieList(
        key: String, language: String
    ): List<SmallMovie> = withContext(Dispatchers.IO) {
        val playList = api.getListOfPosters(key, language = language)
        playList.smallMovieList
    }
}

class MovieDetailSource @Inject constructor(private val api: MovieApi) {
    suspend fun fetchDetailInformationOfMovie(id: Int): MovieInfo = withContext(Dispatchers.IO) {
        val infoOfMovie = api.getMovieByID(id)
        infoOfMovie
    }
}