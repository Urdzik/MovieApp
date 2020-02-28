package com.example.movieapp.model.network

import retrofit2.http.GET


//A retrofit service to fetch movie playlist.
interface MovieApi {
    @GET("movie.json")
    suspend fun getPropertyAsync(): List<NetworkMovie>
}


