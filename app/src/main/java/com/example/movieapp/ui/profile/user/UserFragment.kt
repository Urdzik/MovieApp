package com.example.movieapp.ui.profile.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapp.R
import com.example.movieapp.databinding.UserFragmentBinding



class UserFragment : Fragment() {

    lateinit var binding: UserFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserFragmentBinding.inflate(layoutInflater)

        val args = UserFragmentArgs.fromBundle(requireArguments()).thisUser


        return binding.root
    }


}
