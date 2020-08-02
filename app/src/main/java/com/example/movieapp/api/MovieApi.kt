package com.example.movieapp.api

import com.example.movieapp.model.network.movie.MovieInfo
import com.example.movieapp.model.network.movie.Results
import com.example.movieapp.model.network.movie.SmallMovie
import com.example.movieapp.utils.API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//A retrofit service to fetch movie playlist.
interface MovieApi {

    @GET("movie/{category}")
     fun getPropertyAsync(
        @Path("category") category: String,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<Results>


    @GET("movie/{category}")
    fun getListOfPosters(
        @Path("category") category: String,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String
    ): Single<SmallMovie>


    @GET("movie/{id}?api_key=$API_KEY&language=ru")
     fun getMovieByID(@Path("id") id: Int): Single<MovieInfo>
}