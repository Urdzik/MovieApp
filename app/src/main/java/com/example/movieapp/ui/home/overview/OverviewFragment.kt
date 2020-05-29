package com.example.movieapp.ui.home.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.OverviewFragmentBinding
import com.example.movieapp.utils.adapters.*
import com.google.android.material.snackbar.Snackbar
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import javax.inject.Inject


class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: OverviewViewModel
    lateinit var binding: OverviewFragmentBinding

    lateinit var recViewingMovieAdapter: RecViewingMovieAdapter
    lateinit var topRatedMovieAdapter: TopRatedMovieAdapter
    lateinit var popularMovieAdapter: PopularMovieAdapter
    lateinit var nowPlayingMovieAdapter: NowPlayingMovieAdapter

    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        binding = OverviewFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recViewingRvViewing()
        topRatedRvViewing()
        popularRvViewing()
        nowPlayingRvViewing()

        //Navigate to Detail Activity
        viewModel.navigateToSelectProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(
                        it.id
                    )
                )
                viewModel.displayPropertyDetailsCompleted()
            }
        })

        //Looking for the internet connection
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
            if (it) onNetworkError()
        })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

    }

    //Saving scroll position
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::binding.isLateinit) return

            outState.putIntArray(
                "ARTICLE_SCROLL_POSITION",
                intArrayOf(binding.scrollView.scrollX, binding.scrollView.scrollY)
            )
    }

    //Set scroll position
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val position = savedInstanceState?.getIntArray("ARTICLE_SCROLL_POSITION")
        if (position != null) binding.scrollView.post {
            binding.scrollView.scrollTo(
                position[0],
                position[1]
            )
        }
    }

    //Function will show a toast when there is no internet
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            errorSnackbar = Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
            errorSnackbar?.show()
        }
    }

    //Work with recommended RV
    private fun recViewingRvViewing() {
        binding.piker.adapter =
            RecViewingMovieAdapter(RecViewingMovieAdapter.ClickListener {
                viewModel.displayPropertyDetails(it)
            })

        //Add animation for RV
        binding.piker.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build()
        )

        recViewingMovieAdapter = binding.piker.adapter as RecViewingMovieAdapter

        viewModel.recViewingPlayList.observe(viewLifecycleOwner, Observer {
            recViewingMovieAdapter.submitList(it)
        })
    }

    //Work with top rated RV
    private fun topRatedRvViewing() {
        binding.recyclerTopRated.adapter = TopRatedMovieAdapter(MovieListener {
            viewModel.displayPropertyDetails(it)
        })

        topRatedMovieAdapter = binding.recyclerTopRated.adapter as TopRatedMovieAdapter

        viewModel.topRatedPlayList.observe(viewLifecycleOwner, Observer {
            topRatedMovieAdapter.addHeaderAndSubmitList(it)
        })
    }

    //Work with popular RV
    private fun popularRvViewing() {
        binding.recyclerPopular.adapter = PopularMovieAdapter(PopularMovieAdapter.MovieListener {
            viewModel.displayPropertyDetails(it)
        })

        popularMovieAdapter = binding.recyclerPopular.adapter as PopularMovieAdapter

        viewModel.popularPlayList.observe(viewLifecycleOwner, Observer {
            popularMovieAdapter.addHeaderAndSubmitList(it)
        })
    }

    //Work with now playing RV
    private fun nowPlayingRvViewing() {
        binding.recyclerNowPlaying.adapter =
            NowPlayingMovieAdapter(NowPlayingMovieAdapter.MovieListener {
                viewModel.displayPropertyDetails(it)
            })

        nowPlayingMovieAdapter = binding.recyclerNowPlaying.adapter as NowPlayingMovieAdapter

        viewModel.nowPlayingPlayList.observe(viewLifecycleOwner, Observer {
            nowPlayingMovieAdapter.addHeaderAndSubmitList(it)
        })
    }
}