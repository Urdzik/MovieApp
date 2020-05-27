package com.example.movieapp.model.network.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/** Data class for Retrofit request from OverviewFragment and getting small data of movie  */

data class SmallMovie(
    @SerializedName("results") val smallMovieList: List<SmallMovieList>,
    @SerializedName("page") val page: Int
)


data class  SmallMovieList constructor(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average")val voteAverage: Float,
    @SerializedName("backdrop_path") val backdropPath: String
){
    constructor(): this(0,"","", 0.0F, "")
}