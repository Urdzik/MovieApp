package com.example.movieapp.ui.profile.user.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {


    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val binding = SettingsFragmentBinding.inflate(inflater)

        val args = SettingsFragmentArgs.fromBundle(requireArguments())


        binding.viewModel = viewModel
        return binding.root
    }



}
