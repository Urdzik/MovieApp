package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.SmallMovie
import com.example.movieapp.utils.API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/multi")
    fun getListOfPosters(
        @Query("query") query: String,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Single<SmallMovie>
}