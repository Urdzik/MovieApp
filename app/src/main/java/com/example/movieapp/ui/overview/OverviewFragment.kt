package com.example.movieapp.ui.overview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.OverviewFragmentBinding
import com.example.movieapp.ui.detail.DetailActivity
import com.example.movieapp.utils.adapters.MovieAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: OverviewViewModel
    lateinit var binding: OverviewFragmentBinding
    lateinit var adapter: MovieAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager

    private var errorSnackbar: Snackbar? = null
    private var popularMoviesPage = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this)
        binding = OverviewFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)
        sendNewMovieList()

        //Listener of recycler view click
        binding.recyclerTopRated.adapter =
            MovieAdapter(
                MovieAdapter.ClickListener {
                    viewModel.displayPropertyDetails(
                        it
                    )
                },
                mutableListOf()
            )
        popularMoviesLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerTopRated.layoutManager = popularMoviesLayoutMgr
        adapter = binding.recyclerTopRated.adapter as MovieAdapter

        viewModel.playList.observe(viewLifecycleOwner, Observer {
            adapter.appendMovies(it)
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
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
                if (it) onNetworkError()
            })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }


    //Function will show a toast when there is no internet
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            errorSnackbar = Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
            errorSnackbar?.show()
        }
    }

    fun sendNewMovieList() {
        viewModel.getMovieList(popularMoviesPage, "ru")
        attachPopularMoviesOnScrollListener()
    }


    fun attachPopularMoviesOnScrollListener() {
        binding.recyclerTopRated.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    binding.recyclerTopRated.removeOnScrollListener(this)
                    popularMoviesPage++
                    sendNewMovieList()
                }
            }
        })
    }

}