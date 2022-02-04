package com.example.movieapp.presentation.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

//    val genres: io.reactivex.rxjava3.core.Single<List<Genre>>
//        get() = api.getGenres().map { it.genres }.cache().subscribeOn(Schedulers.io())
//
//    fun getSearchResult(query: String): Single<List<SearchItem>> {
//        return Single.create<List<SearchItem>> { s ->

//            api.getListOfPosters(query)
//                .map(SearchResponse::results)

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
//                .subscribe({
//                    s.onSuccess(it)
//                }, {
//                    s.onError(it)
//                })
        }
//    }
//}