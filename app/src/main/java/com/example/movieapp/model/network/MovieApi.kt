package com.example.movieapp.model.network

import retrofit2.http.GET


//A retrofit service to fetch movie playlist.
interface MovieApi {
    @GET("now_playing?api_key=26f381d6ab8dd659b22d983cab9aa255&language=ru-US&page=1")
    suspend fun getPropertyAsync(): Results
}