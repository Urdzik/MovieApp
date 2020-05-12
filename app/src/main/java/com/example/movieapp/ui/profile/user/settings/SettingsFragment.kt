package com.example.movieapp.ui.profile.user.settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.movieapp.R
import com.example.movieapp.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {


    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val binding = SettingsFragmentBinding.inflate(inflater)

        val args = SettingsFragmentArgs.fromBundle(requireArguments()).thisUser
        binding.

        binding.viewModel = viewModel
        return binding.root
    }



}
