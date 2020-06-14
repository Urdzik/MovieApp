package com.example.movieapp.model.network.data.news.data

data class NewsResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)