package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.NetworkMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkSource @Inject constructor(
    private val api: MovieApi
) {
    suspend fun retrieveData(): List<NetworkMovie> = withContext(Dispatchers.IO) {
        val playList = api.getPropertyAsync()
       playList.networkMovie
    }
}

class MovieInfoSource @Inject constructor(
    private val api: MovieApi
){
    suspend fun retrieveInfoData(id: Int): MovieInfo = withContext(Dispatchers.IO){
        val infoOfMovie = api.getMovieByID(id)
        infoOfMovie
    }
}