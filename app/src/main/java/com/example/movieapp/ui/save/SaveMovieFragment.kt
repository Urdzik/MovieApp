package com.example.movieapp.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.SaveMovieFragmentBinding

class SaveMovieFragment : Fragment() {

    private lateinit var viewModel: SaveMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SaveMovieFragmentBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(SaveMovieViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}
