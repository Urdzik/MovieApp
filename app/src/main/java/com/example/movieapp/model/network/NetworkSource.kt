package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.ListMovie
import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.SmallMovieList
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