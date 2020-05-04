package com.example.movieapp.ui.profile.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.UserFragmentBinding
import javax.inject.Inject


class UserFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: UserFragmentBinding
    lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        binding = UserFragmentBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        val args = UserFragmentArgs.fromBundle(requireArguments()).thisUser

        viewModel.getUser(args)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


}
