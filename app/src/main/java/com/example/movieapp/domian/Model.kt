package com.example.movieapp.domian

import androidx.room.PrimaryKey

data class Movie(
    @PrimaryKey
    val id: String,

    val title: String,
    val poster: String,
    val year: Int,
    val rated: String,
    val plot: String
)