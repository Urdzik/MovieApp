package movie.app.data.api

import com.example.main.BuildConfig
import movie.app.data.model.movie.MovieInfo
import movie.app.data.model.movie.Results
import movie.app.data.model.movie.SmallMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{category}")
  suspend fun getPropertyAsync(
        @Path("category") category: String,
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Results


    @GET("movie/{category}")
    suspend  fun getListOfPosters(
        @Path("category") category: String,
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String
    ): SmallMovie


    @GET("movie/{id}?api_key=${BuildConfig.API_KEY}&language=ru")
    suspend fun getMovieByID(@Path("id") id: Int): MovieInfo
}