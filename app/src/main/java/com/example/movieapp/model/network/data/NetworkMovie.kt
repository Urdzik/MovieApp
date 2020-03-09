package com.example.movieapp.model.network.data

import android.view.View
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Network object of movie for REST

data class Results(
    @SerializedName("results") val networkMovie: List<NetworkMovie>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int
)

data class NetworkMovie(
    val id: Int,
    val adult: Boolean,
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val poster_path: String,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val release_date: String
)