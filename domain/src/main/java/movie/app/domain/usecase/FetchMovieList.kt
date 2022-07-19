package movie.app.domain.usecase

import com.example.main.data.repository.movie.MovieRepository
import javax.inject.Inject

class FetchMovieList @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(category: String, language: String, page: Int) =
        movieRepository.fetchMovieList(category, language, page)

}