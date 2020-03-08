package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.Results
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//A retrofit service to fetch movie playlist.
interface MovieApi {
    @GET("top_rated?api_key=26f381d6ab8dd659b22d983cab9aa255&language=ru&page=")
    suspend fun getPropertyAsync(@Query("page") page: Int): Results

    @GET("{id}?api_key=26f381d6ab8dd659b22d983cab9aa255&language=ru")
    suspend fun getMovieByID(@Query("id") id: Int): MovieInfo
}