package com.example.movieapp.ui.user.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {

    private var _auth = MutableLiveData<FirebaseAuth>()
    val auth: LiveData<FirebaseAuth>
        get() = _auth

    fun getUserAuth(thisUserAuth: FirebaseAuth) {
        _auth.value = thisUserAuth
    }

    fun revokeAccess() {
        // Firebase sign out
        auth.value?.signOut()

        _auth.value = null
    }

}
