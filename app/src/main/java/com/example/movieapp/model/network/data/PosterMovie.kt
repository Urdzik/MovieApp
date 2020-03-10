package com.example.movieapp.model.network.data

import com.google.gson.annotations.SerializedName

data class PosterMovie(
    @SerializedName("results") val smallMovieList: List<SmallMovieList>,
    @SerializedName("page") val page: Int
)

data class SmallMovieList(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average")val voteAverage: Float,
    @SerializedName("backdrop_path") val backdropPath: String
)