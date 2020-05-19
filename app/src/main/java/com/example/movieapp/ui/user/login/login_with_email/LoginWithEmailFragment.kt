package com.example.movieapp.ui.user.login.login_with_email


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.movieapp.R

class LoginWithEmailFragment : Fragment() {



    private lateinit var viewModel: LoginWithEmailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(LoginWithEmailViewModel::class.java)

        return inflater.inflate(R.layout.login_with_email_fragment, container, false)
    }


}
