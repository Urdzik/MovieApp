package com.example.movieapp.ui.login.sing_in

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.movieapp.R
import com.example.movieapp.databinding.SingInFragmentBinding

class SingInFragment : Fragment() {

    private lateinit var viewModel: SingInViewModel
    private lateinit var binding: SingInFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SingInFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(SingInViewModel::class.java)
        return binding.root
    }



}
