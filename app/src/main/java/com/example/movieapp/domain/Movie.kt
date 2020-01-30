package com.example.movieapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @see database for objects that are mapped to the database
 * @see network for objects that parse or prepare network calls
 * */

//Movie represent a movie that can be played.
@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val poster: String,
    val year: Int,
    val rated: String,
    val plot: String
) : Parcelable {
    val isR
        get() = rated == "R"
}