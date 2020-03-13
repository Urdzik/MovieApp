package com.example.movieapp.model.network.data

import com.google.gson.annotations.SerializedName

/** Data class for Retrofit request from OverviewFragment and getting small data of movie  */

data class SmallMovie(
    @SerializedName("results") val smallMovieList: List<SmallMovieList>,
    @SerializedName("page") val page: Int
)

data class SmallMovieList(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average")val voteAverage: Float,
    @SerializedName("backdrop_path") val backdropPath: String
)