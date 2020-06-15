package com.example.movieapp.model.network.data.search

import io.reactivex.Observable

data class SearchResponse(
    val page: Int,
    val results: List<SearchItem>,
    val total_pages: Int,
    val total_results: Int
)

fun streams() {
    val s1 = Observable.just(true)
}