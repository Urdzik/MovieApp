package com.example.movieapp.model.network

import com.example.movieapp.model.database.DatabaseMovie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Network object of movie for REST
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
)

//Convert network result to database object
fun List<NetworkMovie>.asDatabaseModal(): List<DatabaseMovie> {
    return map {
        DatabaseMovie(
            id = it.id,
            title = it.title,
            poster = it.poster,
            year = it.year,
            rated = it.rated,
            plot = it.plot,
            genre = it.genre,
            time = it.time,
            language = it.language,
            writer = it.writer,
            actors = it.actors
        )
    }
}