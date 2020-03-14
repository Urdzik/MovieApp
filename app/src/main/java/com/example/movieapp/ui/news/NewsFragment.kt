package com.example.movieapp.ui.news


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.NewsFragmentBinding
import com.example.movieapp.utils.ALL_NEWS
import com.example.movieapp.utils.MOVIE_NEWS
import com.example.movieapp.utils.TV_NEWS
import javax.inject.Inject

class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        val binding = NewsFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        viewModel.loadData()

        viewModel.getWeeklyNews(ALL_NEWS).observe(viewLifecycleOwner, Observer {
            println("size all news: ${it[1].title}")
        })

        viewModel.getWeeklyNews(MOVIE_NEWS).observe(viewLifecycleOwner, Observer {
            println("movie news: ${it[0].title}")
        })
//        viewModel.getDailyByType(MOVIE_NEWS)?.observe(viewLifecycleOwner, Observer { list ->
//            list.forEach {
//                Log.d("daily", it.title)
//            }
//        })
//
//        viewModel.getWeeklyByType(TV_NEWS)?.observe(viewLifecycleOwner, Observer { list ->
//            list.forEach {
//                Log.d("weekly", it.title)
//            }
//        })

        return binding.root
    }
}
