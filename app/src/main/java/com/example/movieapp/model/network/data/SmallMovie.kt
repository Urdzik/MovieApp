package com.example.movieapp.model.network.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/** Data class for Retrofit request from OverviewFragment and getting small data of movie  */

data class SmallMovieList(
    @SerializedName("results") val smallMovieList: List<SmallMovie>,
    @SerializedName("page") val page: Int
)
@Parcelize
data class SmallMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average")val voteAverage: Float,
    @SerializedName("backdrop_path") val backdropPath: String
): Parcelable
