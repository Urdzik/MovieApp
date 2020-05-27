package com.example.movieapp.ui.user.profile

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.data.SmallMovieList
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {


    private var _navigateToSelectSaveProperty = MutableLiveData<SmallMovieList>()
    val navigateToSelectSaveProperty: LiveData<SmallMovieList>
        get() = _navigateToSelectSaveProperty

    private var _movieOfSave = MutableLiveData<List<SmallMovieList>>()
    val movieOfSave: LiveData<List<SmallMovieList>>
        get() = _movieOfSave

    private var _currentUser = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser>
        get() = _currentUser

    private var _authUser = MutableLiveData<FirebaseAuth>()
    val authUser: LiveData<FirebaseAuth>
        get() = _authUser

    private var _googleSignInClient = MutableLiveData<GoogleSignInClient>()
    val googleSignInClient: LiveData<GoogleSignInClient>
        get() = _googleSignInClient

    //LiveData for show Progress Bar
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    //LiveData for show internet error
    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    var test = MutableLiveData(false)

    val errorClickListener = View.OnClickListener { fetchMovieOfSave() }


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
            db.collection("users")
                .document(currentUser.value!!.uid)
                .collection("movie")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        _movieOfSave.value = result.toObjects(SmallMovieList::class.java)

                    }
                }.addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents.", exception)
                }
        }
    }
    fun displayPropertyDetails(movie: SmallMovieList) {
        _navigateToSelectSaveProperty.value = movie
    }

    fun displayPropertyDetailsCompleted() {
        _navigateToSelectSaveProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        println("Finish")
    }


}
