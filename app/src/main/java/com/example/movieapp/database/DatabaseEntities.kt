package com.example.movieapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.domian.Movie

@Entity(tableName = "database_movie")
data class DatabaseMovie(
    @PrimaryKey
    val id: String,

    val title: String,
    val poster: String,
    val year: Int,
    val rated: String,
    val plot: String
)

    fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
        return map {
            Movie(
                id = it.id,
                title = it.title,
                poster = it.poster,
                year = it.year,
                rated = it.rated,
                plot = it.plot
            )
        }
    }
