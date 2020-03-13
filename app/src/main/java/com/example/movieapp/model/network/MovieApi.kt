package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.SmallMovie
import com.example.movieapp.model.network.data.Results
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    //Fetching movie list with more information
    @GET("movie/{category}")
    suspend fun getPropertyAsync(
        @Path("category") category: String,
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Results

    //Fetching movie list
    @GET("movie/{category}")
    suspend fun getListOfPosters(
        @Path("category") category: String,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): SmallMovie

    //Fetching detail information of movie
    @GET("movie/{id}?api_key=26f381d6ab8dd659b22d983cab9aa255&language=ru")
    suspend fun getMovieByID(@Path("id") id: Int): MovieInfo
}