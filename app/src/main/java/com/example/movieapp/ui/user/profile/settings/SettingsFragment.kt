package com.example.movieapp.ui.user.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory

import com.example.movieapp.databinding.SettingsFragmentBinding
import com.example.movieapp.ui.user.profile.ProfileViewModel
import javax.inject.Inject

class SettingsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        val binding = SettingsFragmentBinding.inflate(inflater)

        val args = SettingsFragmentArgs.fromBundle(requireArguments())


        binding.viewModel = viewModel
        return binding.root
    }



}
