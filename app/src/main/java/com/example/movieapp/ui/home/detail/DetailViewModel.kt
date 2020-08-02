package com.example.movieapp.ui.home.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.network.movie.MovieInfo
import com.example.movieapp.model.network.movie.SmallMovieList
import com.example.movieapp.repository.NetworkRepository
import com.example.movieapp.repository.NetworkRepositoryImpl
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieDetailSource: NetworkRepository) :
    ViewModel() {

    //LiveData object of movie
    private val _selectProperty = MutableLiveData<MovieInfo>()
    val selectProperty: LiveData<MovieInfo>
        get() = _selectProperty

    private val _test = MutableLiveData(false)
    val test: LiveData<Boolean>
        get() = _test

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId


    fun getSelectedMovieById(id: Int) {
            movieDetailSource.fetchDetailInformationOfMovie(id).subscribe({
                _selectProperty.value = it
            },{
                Log.d("DetailViewModel", "$it")
            })
    }


    fun putMovieInDatabase(){

        val database = Firebase.firestore
        _selectProperty.value?.also {
            val listOfMovie =
                SmallMovieList(
                    id = it.id,
                    posterPath = it.poster_path,
                    title = it.title,
                    voteAverage = it.vote_average.toFloat(),
                    backdropPath = it.backdrop_path
                )

            Single.create<DocumentReference>{ sub ->
                database.collection("users").document(userId.value!!).collection("movie")
                    .add(listOfMovie)
                    .addOnSuccessListener { documentReference ->
                       sub.onSuccess(documentReference)
                    }.addOnFailureListener { e ->
                        sub.onError(e)
                    }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("DetailViewModel", "DocumentSnapshot added with ID: ${it.id}")
                    _test.value = true
                }, { Log.w("DetailViewModel", "Error adding document", it) })

        }
    }

    fun getUserId(id: String){
        _userId.value = id
    }

     fun checkForSavedMovie(id: String) {
         var i = 0
         val database = Firebase.firestore
         Single.create<QuerySnapshot> { sub ->
             database.collection("users")
                 .document(id)
                 .collection("movie")
                 .get()
                 .addOnSuccessListener { result ->
                     sub.onSuccess(result)
                 }.addOnFailureListener { exception ->
                     sub.onError(exception)
                 }
         }.subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe({
                 for (document in it) {
                     if (selectProperty.value?.title == document.get("title")) {
                         i++
                         _test.value = true
                     } else {
                         if (i == 0) {
                             _test.value = false
                         }
                     }
                 }
             }, {
                 Log.w("DetailViewModel", "Error getting documents.", it)
             })
     }
    }