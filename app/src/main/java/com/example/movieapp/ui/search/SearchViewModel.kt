package com.example.movieapp.ui.search

import androidx.lifecycle.ViewModel
import com.example.movieapp.model.network.SearchApi
import com.example.movieapp.model.network.data.movie.SmallMovieList
import com.example.movieapp.model.network.data.search.Genre
import com.example.movieapp.model.network.data.search.SearchItem
import com.example.movieapp.model.network.data.search.SearchResponse
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val api: SearchApi) : ViewModel() {

//    val genres: io.reactivex.rxjava3.core.Single<List<Genre>>
//        get() = api.getGenres().map { it.genres }.cache().subscribeOn(Schedulers.io())

    private lateinit var allGenres: List<Genre>

    init {
        api.getGenres()
            .map { it.genres }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                allGenres = it
            }, {
                allGenres = emptyList()
            })
    }

    fun getSearchResult(query: String): Single<List<SearchItem>> {
        return api.getListOfPosters(query)
            .map(SearchResponse::results)
//            .map { items ->
//                items.map { item ->
//                    item.apply {
//                        genres = genreIds.map { id ->
//                            allGenres.find { genre -> genre.id == id }?.name ?: ""
//                        }.filterNot(String::isNullOrEmpty)
//                    }
//                }
//            }
    }
}