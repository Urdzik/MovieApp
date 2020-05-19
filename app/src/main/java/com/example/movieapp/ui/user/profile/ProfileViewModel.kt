package com.example.movieapp.ui.user.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {

    private var _currentUser = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser>
        get() = _currentUser

    private var _authUser = MutableLiveData<FirebaseAuth>()
    val authUser: LiveData<FirebaseAuth>
        get() = _authUser

    private var _googleSignInClient = MutableLiveData<GoogleSignInClient>()
    val googleSignInClient: LiveData<GoogleSignInClient>
        get() = _googleSignInClient

    var test = MutableLiveData(false)




    fun getUser(user: FirebaseUser?) {
        _currentUser.value = user
    }

    fun getAuthUser(auth: FirebaseAuth){
        _authUser.value = auth
    }

    fun getGoogleSignInClient(googleSignInClient: GoogleSignInClient){
        _googleSignInClient.value = googleSignInClient
    }

    fun testTrue(){
        test.value = true
    }

    fun testFalse(){
        test.value = false
    }


}
