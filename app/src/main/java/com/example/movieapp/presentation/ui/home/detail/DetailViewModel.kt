package com.example.movieapp.presentation.ui.home.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.movie.MovieInfo
import com.example.movieapp.data.model.movie.SmallMovieList
import com.example.movieapp.domain.usecase.FetchMovieInfo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val fetchMovieInfo: FetchMovieInfo) :
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
    viewModelScope.launch {
      _selectProperty.postValue(fetchMovieInfo.invoke(id))
    }

  }


  fun putMovieInDatabase() {

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

      database.collection("users").document(userId.value!!).collection("movie")
        .add(listOfMovie)
        .addOnSuccessListener { documentReference ->
          _test.value = true
          Log.d("DetailViewModel", "DocumentSnapshot added with ID: ${it.id}")
        }.addOnFailureListener { e ->
          Log.w("DetailViewModel", "Error adding document", e)
        }
    }
  }

  fun getUserId(id: String) {
    _userId.value = id
  }

  fun checkForSavedMovie(id: String) {
    var i = 0
    val database = Firebase.firestore
    database.collection("users")
      .document(id)
      .collection("movie")
      .get()
      .addOnSuccessListener { result ->
        for (document in result) {
          if (selectProperty.value?.title == document.get("title")) {
            i++
            _test.value = true
          } else {
            if (i == 0) {
              _test.value = false
            }
          }
        }      }.addOnFailureListener { exception ->
        Log.w("DetailViewModel", "Error getting documents.", exception)
      }

  }
}