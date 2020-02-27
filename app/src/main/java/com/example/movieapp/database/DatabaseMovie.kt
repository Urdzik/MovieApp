package com.example.movieapp.database

import android.os.Parcelable
import android.view.View
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 *  Database used this class for save entity in database.
 *  This class responsible for writing and reading from database
 *  */

@Parcelize
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
) : Parcelable {

    val plus18: Int
        get() = if (rated == "R") View.GONE else View.VISIBLE

}

