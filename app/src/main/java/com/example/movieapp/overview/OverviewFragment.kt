package com.example.movieapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {


    private val viewModel: OverviewViewModel by lazy {
        val activity = requireNotNull(this.activity) {}
        ViewModelProviders.of(this, OverviewViewModelFactory(activity.application))
            .get(OverviewViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = OverviewFragmentBinding.inflate(inflater)
        val myToolbar = binding.myToolbar
        (activity as AppCompatActivity).setSupportActionBar(myToolbar)


        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel
        binding.recycler.adapter = MovieAdapter(MovieAdapter.ClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComlited()
            }

        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })

        return binding.root
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}
