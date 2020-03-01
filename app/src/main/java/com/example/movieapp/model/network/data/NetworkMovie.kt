package com.example.movieapp.model.network.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

//Network object of movie for REST
@JsonClass(generateAdapter = true)
@Parcelize
data class Results(
    @Json(name = "results") val networkMovie: List<NetworkMovie>,
    @Json(name = "page") val page: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
) : Parcelable

@Parcelize
data class NetworkMovie(
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "voteCount") val voteCount: Int,
    @Json(name = "video") val video: Boolean,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "id") val id: Int,
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String,
    val original_language: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "overview") val overview: String,
   val release_date: String
) : Parcelable