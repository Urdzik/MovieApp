package com.example.movieapp.model.network

import android.os.Parcelable
import android.view.View

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

//Network object of movie for REST
@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkMovie(
    @Json(name = "imdbID") val id: String,
    @Json(name = "Title") val title: String,
    @Json(name = "Poster") val poster: String,
    @Json(name = "Year") val year: Int,
    @Json(name = "Rated") val rated: String,
    @Json(name = "Plot") val plot: String,
    @Json(name = "Genre") val genre: String,
    @Json(name = "Runtime") val time: String,
    @Json(name = "Language") val language: String,
    @Json(name = "Writer") val writer: String,
    @Json(name = "Actors") val actors: String
): Parcelable
{
    val plus18: Int
        get() = if (rated == "R") View.GONE else View.VISIBLE
}

