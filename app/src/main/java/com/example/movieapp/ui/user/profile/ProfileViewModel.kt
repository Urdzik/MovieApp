package com.example.movieapp.ui.user.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.data.SmallMovieList
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {
    init {
        println("Create viewMode")
    }

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

    init {

    }


    fun getUser(user: FirebaseUser?) {
        _currentUser.value = user!!
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

    fun fetchMovieOfSave() {
        val db = Firebase.firestore
        viewModelScope.launch {
            db.collection("users").document(currentUser.value!!.uid).collection("movie")
                .get()
                .addOnSuccessListener { result ->
                    Log.i("TAG", "1")
                   for(document in result){
                      val obj =  result.toObjects(SmallMovieList::class.java)
                       Log.i("TAG", "2")
                       obj.forEach {
                           Log.i("TAG", "${it.title}")
                       }
                   }
                }.addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents.", exception)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("Finish")
    }


}
