package com.example.movieapp.domain.usecase

import com.example.movieapp.data.repository.movie.MovieRepository
import org.intellij.lang.annotations.Language
import javax.inject.Inject

class FetchMovieList @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(category: String, language: String, page: Int) =
        movieRepository.fetchMovieList(category, language, page)

}