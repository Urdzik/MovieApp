package com.example.movieapp.ui.overview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.OverviewFragmentBinding
import com.example.movieapp.ui.detail.DetailActivity
import com.example.movieapp.utils.MovieAdapter
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)

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
            it?.let {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("movie", it.id)
                startActivity(intent)
                viewModel.displayPropertyDetailsCompleted()
            }
        })

        //Looking for the internet connection
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> {
                if (it) onNetworkError()
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