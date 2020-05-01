package com.example.movieapp.ui.profile.login.login_with_email

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.movieapp.R

class LoginWithEmailFragment : Fragment() {

    companion object {
        fun newInstance() = LoginWithEmailFragment()
    }

    private lateinit var viewModel: LoginWithEmailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_with_email_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginWithEmailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
