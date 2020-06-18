package com.example.movieapp.model.network.data.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchItem(
    @SerializedName("genre_ids") val genreIds: List<Int>,
    var genres: List<String> = listOf(),
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("name") val name: String,
    @SerializedName("vote_average")val voteAverage: Float,
    @SerializedName("backdrop_path") val backdropPath: String
): Parcelable