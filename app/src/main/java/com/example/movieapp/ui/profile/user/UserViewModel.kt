package com.example.movieapp.ui.profile.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.network.SmallMovieListSource
import com.example.movieapp.model.network.data.SmallMovieList
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserViewModel @Inject constructor() : ViewModel(){

    private var _currentUser = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser>
        get() = _currentUser

    fun getUser(user: FirebaseUser){
        _currentUser.value = user
    }

}