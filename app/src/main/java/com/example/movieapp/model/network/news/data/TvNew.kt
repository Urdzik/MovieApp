package com.example.movieapp.model.network.news.data

import com.google.gson.annotations.SerializedName

data class TvNew(
    @SerializedName("backdrop_path") val backdropPath: String,
    val first_air_date: String,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Float,
    val vote_count: Int
)