package com.example.movieapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.DetailFragmentBinding
import java.util.*


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        val application = requireNotNull(activity).application

        val binding = DetailFragmentBinding.inflate(inflater)
        val movieProperty = DetailFragmentArgs.fromBundle(arguments!!).movieProperty
        val viewModelFactory = DetailViewModelFactory(movieProperty, application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)


        val myToolbar = binding.toolbar

        (activity as AppCompatActivity).setSupportActionBar(myToolbar)
        Objects.requireNonNull((activity as AppCompatActivity).supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        ((activity as AppCompatActivity).supportActionBar)?.apply {
            setHomeButtonEnabled(true)
            setDisplayShowTitleEnabled(false)
            title = movieProperty.title
        }
        myToolbar.setNavigationOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToOverviewFragment())
        }




        binding.viewModel = viewModel
        return binding.root
    }


}
