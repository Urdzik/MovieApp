package com.example.movieapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.OverviewFragmentBinding
import java.util.*

class OverviewFragment : Fragment() {


    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = OverviewFragmentBinding.inflate(inflater)
        val myToolbar = binding.myToolbar

        (activity as AppCompatActivity).setSupportActionBar(myToolbar)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        binding.recycler.adapter = MovieAdapter(MovieAdapter.ClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectProperty.observe(this, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComlited()
            }

        })






        return binding.root
    }


}
