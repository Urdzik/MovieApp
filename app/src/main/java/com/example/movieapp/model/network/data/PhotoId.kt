package com.example.movieapp.model.network.data

import com.google.gson.annotations.SerializedName

data class PhotoId(
    @SerializedName("results") val small: List<Small>,
    val page: Int

)

data class Small(
    val id: Int,
    val poster_path: String
)