package com.example.movieapp.repository

import com.example.movieapp.api.MovieApi
import com.example.movieapp.model.network.movie.ListMovie
import com.example.movieapp.model.network.movie.MovieInfo
import com.example.movieapp.model.network.movie.SmallMovieList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: MovieApi) : NetworkRepository {
    override fun fetchMovieList(
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

    override fun fetchSmallMovieList(
        categories: List<String>,
        key: String,
        language: String
    ): Single<List<List<SmallMovieList>>> {
        return Flowable.fromIterable(categories)
            .concatMap { category ->
                api.getListOfPosters(category, key, language)
                    .toFlowable()
                    .subscribeOn(Schedulers.io())
            }
            .map { it.smallMovieList }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun fetchDetailInformationOfMovie(id: Int): Single<MovieInfo> {
        return api.getMovieByID(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}


