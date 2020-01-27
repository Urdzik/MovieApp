package com.example.movieapp.domian

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

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