package com.example.movieapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.DetailFragmentBinding


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DetailFragmentBinding.inflate(inflater)

        //Get movie object
        val movie = DetailFragmentArgs.fromBundle(arguments!!).movie

        val viewModel = ViewModelProvider(this, DetailViewModelFactory(movie))
            .get(DetailViewModel::class.java)

        // Create Toolbar and button of back in toolbar
        val myToolbar = binding.toolbar

        (activity as AppCompatActivity).apply {
            setSupportActionBar(myToolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
            title = movie.title
        }

        //Button of back
        myToolbar.setNavigationOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToOverviewFragment())
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}
