package com.example.movieapp.model.network

import com.example.movieapp.model.network.data.ListMovie
import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.SmallMovieList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieListSource @Inject constructor(private val api: MovieApi) {
    fun fetchMovieList(category: String, key: String, language: String, page: Int): Single<List<ListMovie>> {
        return api.getPropertyAsync(category, key, language, page)
            .map { it.networkMovie }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

class SmallMovieListSource @Inject constructor(private val api: MovieApi) {
    fun fetchSmallMovieList(
        categories: List<String>,
        key: String,
        language: String
    ): Single<List<List<SmallMovieList>>> {
        return Flowable.fromIterable(categories)
            .flatMap { category ->
                api.getListOfPosters(category, key, language)
                    .toFlowable()
                    .subscribeOn(Schedulers.io())
            }
            .map { it.smallMovieList }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
    }

}

class MovieDetailSource @Inject constructor(private val api: MovieApi) {
    fun fetchDetailInformationOfMovie(id: Int): Single<MovieInfo> {
        return api.getMovieByID(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}



