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
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("voteCount") val voteCount: Int,
    @SerializedName("video") val video: Boolean,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("id") val id: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String
) : Parcelable