package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.Results
import retrofit2.http.GET
import retrofit2.http.Path


//A retrofit service to fetch movie playlist.
interface MovieApi {
    @GET("popular?api_key=26f381d6ab8dd659b22d983cab9aa255&language=en-US&page=1")
    suspend fun getPropertyAsync(): Results

    @GET("{id}?api_key=26f381d6ab8dd659b22d983cab9aa255&language=en-US")
    suspend fun getMovieByID(@Path("id") id: Int): MovieInfo
}