package com.example.movieapp.domain

import android.os.Parcelable
import android.view.View
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
    val plot: String,
    val genre: String,
    val time: String,
    val language: String,
    val writer: String,
    val actors: String
) : Parcelable {

    val plus18 = if (rated == "R") View.GONE else View.VISIBLE
}