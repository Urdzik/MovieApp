package com.example.movieapp.ui.home.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.MovieDetailSource
import com.example.movieapp.model.network.data.MovieInfo
import com.example.movieapp.model.network.data.SmallMovieList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieDetailSource: MovieDetailSource) :
    ViewModel() {

    //LiveData object of movie
    private var _selectProperty = MutableLiveData<MovieInfo>()
    val selectProperty: LiveData<MovieInfo>
        get() = _selectProperty

    fun getSelectedMovieById(id: Int) {
        viewModelScope.launch {
            _selectProperty.value = movieDetailSource.fetchDetailInformationOfMovie(id)
        }
    }

    fun putMovieInDatabase(id: String){
        val database = Firebase.firestore
        _selectProperty.value?.also {
            val listOfMovie = SmallMovieList(
                id = it.id,
                posterPath = it.poster_path,
                title = it.title,
                voteAverage = it.vote_average.toFloat(),
                backdropPath = it.backdrop_path)

            viewModelScope.launch {
                database.document("users/$id")
                    .collection("movies")
                    .add(listOfMovie)
                    .addOnSuccessListener { documentReference ->
                        Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }.addOnFailureListener { e ->
                        Log.w("TAG", "Error adding document", e)
                    }
            }
        }

    }
}