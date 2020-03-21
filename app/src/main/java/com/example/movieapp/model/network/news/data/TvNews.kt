package com.example.movieapp.model.network.news.data

import com.google.gson.annotations.SerializedName

data class TvNews(
    val page: Int,
    @SerializedName("results") val newsList: List<TvNew>,
    val total_pages: Int,
    val total_results: Int
)