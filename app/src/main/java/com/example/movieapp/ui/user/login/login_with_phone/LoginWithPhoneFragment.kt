package com.example.movieapp.ui.user.login.login_with_phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.movieapp.R

class LoginWithPhoneFragment : Fragment() {


    private lateinit var viewModel: LoginWithPhoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(LoginWithPhoneViewModel::class.java)
        return inflater.inflate(R.layout.login_with_phone_fragment, container, false)
    }



}
