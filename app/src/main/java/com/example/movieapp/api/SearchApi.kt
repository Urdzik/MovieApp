package com.example.movieapp.api

import com.example.movieapp.model.network.search.GenreResponse
import com.example.movieapp.model.network.search.SearchResponse
import com.example.movieapp.utils.API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/multi")
    fun getListOfPosters(
        @Query("query") query: String,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = "ru"
    ): Single<SearchResponse>

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = "ru"
    ): Single<GenreResponse>
}