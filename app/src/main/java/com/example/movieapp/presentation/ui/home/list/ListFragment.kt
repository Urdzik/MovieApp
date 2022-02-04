package com.example.movieapp.presentation.ui.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ListFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment() {


    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: ListFragmentBinding

    private lateinit var layoutManager: GridLayoutManager
    private lateinit var movieCategory: String
    private lateinit var adapter: ListMovieAdapter

    private var popularMoviesPage = 1
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ListFragmentBinding.inflate(inflater)
        movieCategory = ListFragmentArgs.fromBundle(requireArguments()).category
        sendNewMovieList(movieCategory)

        setMovieListToRV()

        //Looking for the internet connection
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
            if (it) onNetworkError()
        })


        return binding.root
    }


    //Function will show a toast when there is no internet
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            errorSnackbar = Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(R.string.retry) { viewModel.errorClickListener(movieCategory) }
            errorSnackbar?.show()
        }
    }

    fun sendNewMovieList(category: String) {
        viewModel.getMovieList(category, popularMoviesPage)
        attachPopularMoviesOnScrollListener()
    }

    //Getting movies from next page
    private fun attachPopularMoviesOnScrollListener() {
        binding.recyclerList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    binding.recyclerList.removeOnScrollListener(this)
                    popularMoviesPage++
                    sendNewMovieList(movieCategory)
                }
            }
        })
    }

    //Work with RV
    private fun setMovieListToRV() {
        binding.recyclerList.adapter = ListMovieAdapter(ListMovieAdapter.ClickListener {
            viewModel.displayPropertyDetails(it)
        }, mutableListOf())

        adapter = binding.recyclerList.adapter as ListMovieAdapter

        layoutManager = GridLayoutManager(binding.root.context, 2, GridLayoutManager.VERTICAL, false)

        binding.recyclerList.layoutManager = layoutManager

        viewModel.playList.observe(viewLifecycleOwner, Observer {
            adapter.appendMovies(it)
        })

        viewModel.navigateToSelectProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailFragment(it.id)
                )
                viewModel.displayPropertyDetailsCompleted()
            }
        })
    }
}
