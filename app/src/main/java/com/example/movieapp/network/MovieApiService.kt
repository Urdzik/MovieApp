package com.example.movieapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.myjson.com/bins/"

interface MovieApiService {
    @GET("19tvhm")
    suspend fun getPropertyAsync(): List<MovieProperty>
}

object MovieApi {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: MovieApiService = retrofit.create(MovieApiService::class.java)
}