package com.example.movieapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        val activity = requireNotNull(this.activity) {}
        ViewModelProvider(this, OverviewViewModelFactory(activity.application))
            .get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = OverviewFragmentBinding.inflate(inflater)

        //Toolbar
        val myToolbar = binding.myToolbar

        (activity as AppCompatActivity).apply {
            setSupportActionBar(myToolbar)
            title = "Movie App"
        }


        //Listener of recycler view click
        binding.recycler.adapter = MovieAdapter(MovieAdapter.ClickListener {
            viewModel.displayPropertyDetails(it)
        })

        //Navigate to Detail Activity
        viewModel.navigateToSelectProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsCompleted()
            }
        })

        //Looking for the internet connection
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }


    //Function will show a toast when there is no internet
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}