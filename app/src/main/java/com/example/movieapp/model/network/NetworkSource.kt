package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.ListMovie
import com.example.movieapp.model.network.data.SmallMovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkSource @Inject constructor(private val api: MovieApi) {
    suspend fun retrieveData(category: String, key: String, language: String,page: Int
    ): List<ListMovie> = withContext(Dispatchers.IO) {
        val playList = api.getPropertyAsync(category, key, language, page)
       playList.networkMovie
    }
}

class SmallNetworkSource @Inject constructor(private val api: MovieApi) {
    suspend fun retrievePoster(category: String, key: String, language:String
    ): List<SmallMovieList> = withContext(Dispatchers.IO) {
        val playList = api.getListOfPosters(category, key, language)
        playList.smallMovieList
    }
}

class MovieInfoSource @Inject constructor(private val api: MovieApi){
    suspend fun retrieveInfoData(id: Int): MovieInfo = withContext(Dispatchers.IO){
        val infoOfMovie = api.getMovieByID(id)
        infoOfMovie
    }
}