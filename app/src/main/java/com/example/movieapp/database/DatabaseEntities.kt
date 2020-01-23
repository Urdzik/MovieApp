package com.example.movieapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

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