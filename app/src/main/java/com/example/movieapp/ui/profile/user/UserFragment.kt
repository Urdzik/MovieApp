package com.example.movieapp.ui.profile.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.UserFragmentBinding


class UserFragment : Fragment() {

    lateinit var binding: UserFragmentBinding
    lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserFragmentBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val args = UserFragmentArgs.fromBundle(requireArguments()).thisUser

        viewModel.getUser(args)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


}
