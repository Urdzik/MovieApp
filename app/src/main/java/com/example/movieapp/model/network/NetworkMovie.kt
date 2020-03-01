package com.example.movieapp.model.network

import android.os.Parcelable
import android.view.View

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

//Network object of movie for REST
@JsonClass(generateAdapter = true)
data class Results(
    @Json(name = "results") val networkMovie: List<NetworkMovie>,
    @Json(name = "page") val page: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)

@Parcelize
data class NetworkMovie(
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "voteCount") val voteCount: Int,
    @Json(name = "video") val video: Boolean,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "id") val id: Int,
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") val releaseDate: String
) : Parcelable {
    val plus18: Int
        get() = if (adult) View.VISIBLE else View.GONE
}