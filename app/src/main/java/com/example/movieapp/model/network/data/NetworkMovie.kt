package com.example.movieapp.model.network.data

import android.view.View
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Network object of movie for REST
@JsonClass(generateAdapter = true)
data class Results(
    @Json(name = "results") val networkMovie: List<NetworkMovie>,
    @Json(name = "page") val page: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
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
){
    val plus18
    get() = if (adult) View.VISIBLE  else View.GONE
}