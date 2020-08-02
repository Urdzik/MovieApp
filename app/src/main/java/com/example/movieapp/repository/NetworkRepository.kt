package com.example.movieapp.repository

import com.example.movieapp.model.network.movie.ListMovie
import com.example.movieapp.model.network.movie.MovieInfo
import com.example.movieapp.model.network.movie.SmallMovieList
import io.reactivex.rxjava3.core.Single

interface NetworkRepository {

    fun fetchMovieList(
        category: String,
        key: String,
        language: String,
        page: Int
    ): Single<List<ListMovie>>

    fun fetchSmallMovieList(
        categories: List<String>,
        key: String,
        language: String
    ): Single<List<List<SmallMovieList>>>

    fun fetchDetailInformationOfMovie(id: Int): Single<MovieInfo>
}