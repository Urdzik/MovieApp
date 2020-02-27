package com.example.movieapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory




//Main entry point for network access
object MovieApi {

    private const val BASE_URL = "https://raw.githubusercontent.com/Urdzik/helper/master/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: MovieApiService = retrofit.create(MovieApiService::class.java)
}