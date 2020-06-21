package com.example.movieapp.ui.search

import androidx.lifecycle.ViewModel
import com.example.movieapp.model.network.SearchApi
import com.example.movieapp.model.network.data.search.SearchItem
import com.example.movieapp.model.network.data.search.SearchResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val api: SearchApi) : ViewModel() {

//    val genres: io.reactivex.rxjava3.core.Single<List<Genre>>
//        get() = api.getGenres().map { it.genres }.cache().subscribeOn(Schedulers.io())

    fun getSearchResult(query: String): Single<List<SearchItem>> {
        return api.getListOfPosters(query).map(SearchResponse::results)
    }



    // .map(SearchResponse::results)
/*
//                .flattenAsFlowable { it.results }
//                .flatMap { r ->
//                    Flowable.just(r.genreIds)
//                        .flatMap { gId ->
//                            genres.toFlowable().zipWith(
//                                Flowable.just(r),
//                                BiFunction<List<Genre>, SearchItem, SearchItem> { t1, t2 ->
//                                    t2.apply { genres = t1.filter { u -> gId.contains(u.id) }.map(Genre::name) }
//                                }
//                            )
//                        }
//                }
//                .toList()
 */

//                .subscribe({
//                    s.onSuccess(it)
//                }, {
//                    s.onError(it)
//                })
}