package movie.app.domain.usecase

import com.example.main.data.repository.movie.MovieRepository
import javax.inject.Inject

class FetchMovieShortList @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(categories: List<String>, language: String) =
        movieRepository.fetchSmallMovieList(categories, language)

}