package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.movie.ListMovie
import com.example.movieapp.model.network.data.movie.MovieInfo
import com.example.movieapp.model.network.data.movie.ParentListMovie
import com.example.movieapp.model.network.data.movie.SmallMovieList
import com.example.movieapp.model.network.data.search.Genre
import com.example.movieapp.model.network.data.search.GenreResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieListSource @Inject constructor(private val api: MovieApi) {
    fun fetchMovieList(
        category: String,
        key: String,
        language: String,
        page: Int
    ): Single<List<ListMovie>> {
        return api.getPropertyAsync(category, key, language, page)
            .map { it.networkMovie }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

class SmallMovieListSource @Inject constructor(
    private val api: MovieApi
) {

    private val titleCategoryMap = hashMapOf(
        1 to ("Сейчас в кино" to "now_playing"),
        2 to ("Топ рейтинг" to "top_rated"),
        3 to ("Популярное" to "popular"),
        4 to ("Рекомендации" to "upcoming")
    )

    fun fetchSmallMovieList(
        categories: List<String>,
        language: String
    ): Single<List<ParentListMovie>> {
        val moviesSource = fetchMovies(categories, language)
        val genresSource = fetchGenres(language)

        return moviesSource.zipWith(genresSource,
            BiFunction { movies: List<List<SmallMovieList>>, genres: List<Genre> ->
                movies.mapIndexed { index, list ->
                    ParentListMovie(
                        titleCategoryMap[index]?.first ?: "",
                        titleCategoryMap[index]?.second ?: "",
                        list.withGenres(genres)
                    )
                }
            }).observeOn(AndroidSchedulers.mainThread())
    }

    private fun fetchMovies(
        categories: List<String>,
        language: String
    ): Single<List<List<SmallMovieList>>> {
        return Flowable.fromIterable(categories)
            .concatMap { category ->
                api.getListOfPosters(category = category, language = language)
                    .toFlowable()
                    .subscribeOn(Schedulers.io())
            }
            .map { it.smallMovieList }
            .toList()
    }

    private fun fetchGenres(language: String): Single<List<Genre>> {
        return api.getGenres(language = language)
            .map(GenreResponse::genres)
            .subscribeOn(Schedulers.io())
    }
}

class MovieDetailSource @Inject constructor(private val api: MovieApi) {
    fun fetchDetailInformationOfMovie(id: Int): Single<MovieInfo> {
        return api.getMovieByID(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun List<SmallMovieList>.withGenres(allGenres: List<Genre>): List<SmallMovieList> {
    return map { item ->
        item.apply {
            genres = genreIds.map { id ->
                allGenres.find { genre -> genre.id == id }?.name ?: ""
            }.filterNot(String::isNullOrEmpty)
        }
    }
}