package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.movie.ListMovie
import com.example.movieapp.model.network.data.movie.MovieInfo
import com.example.movieapp.model.network.data.movie.SmallMovieList
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieListSource @Inject constructor(val api: MovieApi) {


    suspend fun fetchMovieList(
        category: String,
        key: String,
        language: String,
        page: Int
    ): List<ListMovie> {
        return api.getPropertyAsync(category, key, language, page).networkMovie
    }


    suspend fun fetchSmallMovieList(
        categories: List<String>,
        key: String,
        language: String
    ): Flow<List<List<SmallMovieList>>> =
        flow {
           emit( categories.map { s ->
                    api.getListOfPosters(s, key, language).smallMovieList
            }
           )

        }


    suspend fun fetchDetailInformationOfMovie(id: Int): MovieInfo = api.getMovieByID(id)

}