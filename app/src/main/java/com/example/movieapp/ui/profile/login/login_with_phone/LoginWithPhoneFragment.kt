package com.example.movieapp.ui.profile.login.login_with_phone

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.movieapp.R

class LoginWithPhoneFragment : Fragment() {

    companion object {
        fun newInstance() = LoginWithPhoneFragment()
    }

    private lateinit var viewModel: LoginWithPhoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_with_phone_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginWithPhoneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
