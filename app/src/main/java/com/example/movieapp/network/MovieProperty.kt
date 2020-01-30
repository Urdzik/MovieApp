package com.example.movieapp.network

import com.example.movieapp.database.DatabaseMovie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Network object of movie for REST
@JsonClass(generateAdapter = true)
data class MovieProperty(
    @Json(name = "imdbID") val id: String,
    @Json(name = "Title") val title: String,
    @Json(name = "Poster") val poster: String,
    @Json(name = "Year") val year: Int,
    @Json(name = "Rated") val rated: String,
    @Json(name = "Plot") val plot: String
)

//Convert network result to database object
fun List<MovieProperty>.asDatabaseModal(): List<DatabaseMovie> {
    return map {
        DatabaseMovie(
            id = it.id,
            title = it.title,
            poster = it.poster,
            year = it.year,
            rated = it.rated,
            plot = it.plot
        )
    }
}