package movie.app.domain.usecase

import com.example.main.data.repository.movie.MovieRepository
import javax.inject.Inject

class FetchMovieInfoUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId: Int) =
        movieRepository.fetchDetailInformationOfMovie(movieId)

}