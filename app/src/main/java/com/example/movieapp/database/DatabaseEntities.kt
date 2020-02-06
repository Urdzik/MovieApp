package com.example.movieapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.domain.Movie

/**
 *  Database used this class for save entity in database.
 *  This class responsible for writing and reading from database
 *  */

@Entity(tableName = "database_movie")
data class DatabaseMovie(
    @PrimaryKey
    val id: String,
    val title: String,
    val poster: String,
    val year: Int,
    val rated: String,
    val plot: String,
    val genre: String,
    val time: String,
    val language: String,
    val writer: String,
    val actors: String
)

//Convert database object to movie object
fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
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
