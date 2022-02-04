package com.example.movieapp.domain.usecase

import com.example.movieapp.data.repository.movie.MovieRepository
import javax.inject.Inject

class FetchMovieInfo @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId: Int) =
        movieRepository.fetchDetailInformationOfMovie(movieId)

}