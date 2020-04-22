package com.example.movieapp.ui.login.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrationFragmentBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)


        return binding.root
    }


}
