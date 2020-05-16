package com.example.movieapp.ui.user.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserViewModel @Inject constructor() : ViewModel(){

    private var _currentUser = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser>
        get() = _currentUser

    private var _buttonOfSettings = MutableLiveData(false)
    val buttonOfSettings: LiveData<Boolean>
        get() = _buttonOfSettings

    fun getUser(user: FirebaseUser){
        _currentUser.value = user
    }

    fun clickToSettingsButton(){
        _buttonOfSettings.value = true
    }
    fun finishedToClickOfSettingsButton(){
        _buttonOfSettings.value = false
    }

}