package movie.app.data.movie

import movie.app.data.model.movie.ListMovie
import movie.app.data.model.movie.MovieInfo
import movie.app.data.model.movie.SmallMovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchMovieList(
        category: String,
        language: String,
        page: Int
    ): List<ListMovie>

    suspend fun fetchSmallMovieList(
        categories: List<String>,
        language: String
    ): Flow<List<List<SmallMovieList>>>

    suspend fun fetchDetailInformationOfMovie(id: Int): MovieInfo
}