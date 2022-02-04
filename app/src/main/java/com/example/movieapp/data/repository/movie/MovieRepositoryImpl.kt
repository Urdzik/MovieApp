package com.example.movieapp.data.repository.movie

import com.example.movieapp.data.api.MovieApi
import com.example.movieapp.data.model.movie.ListMovie
import com.example.movieapp.data.model.movie.MovieInfo
import com.example.movieapp.data.model.movie.SmallMovieList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val api: MovieApi): MovieRepository {

    override suspend fun fetchMovieList(
        category: String,
        language: String,
        page: Int
    ): List<ListMovie> {
        return api.getPropertyAsync(category = category, language = language, page = page).networkMovie
    }

    override suspend fun fetchSmallMovieList(
        categories: List<String>,
        language: String
    ): Flow<List<List<SmallMovieList>>> =
        flow {
            emit( categories.map { s -> api.getListOfPosters(category = s, language = language).smallMovieList })
        }

    override suspend fun fetchDetailInformationOfMovie(id: Int): MovieInfo = api.getMovieByID(id)
}