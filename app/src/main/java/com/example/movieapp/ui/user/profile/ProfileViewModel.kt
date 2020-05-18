package com.example.movieapp.ui.user.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.utils.LOGIN_TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class ProfileViewModel: ViewModel() {

    private var _currentUser = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser>
        get() = _currentUser


    fun getUser(user: FirebaseUser?) {
        _currentUser.value = user

    }


}
