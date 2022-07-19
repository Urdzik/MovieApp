package movie.app.data.movie

import movie.app.data.api.MovieApi
import movie.app.data.model.movie.ListMovie
import movie.app.data.model.movie.MovieInfo
import movie.app.data.model.movie.SmallMovieList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class MovieRepositoryImpl @Inject constructor(val api: MovieApi): MovieRepository {

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

