package com.example.movieapp.domian

import androidx.room.PrimaryKey

data class Movie(
    val id: String,
    val title: String,
    val poster: String,
    val year: Int,
    val rated: String,
    val plot: String
){
    val isR
        get() = rated == "R"
}