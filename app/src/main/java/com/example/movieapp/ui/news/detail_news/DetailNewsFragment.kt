package com.example.movieapp.ui.news.detail_news


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.NewsFragmentBinding
import com.example.movieapp.utils.*
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject

class DetailNewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailNewsViewModel

    companion object {
        fun newInstance() = DetailNewsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        val binding = NewsFragmentBinding.inflate(inflater)
        progressBar?.show()

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailNewsViewModel::class.java)

        viewModel.loadData()

        viewModel.getWeeklyNews(ALL_NEWS).observe(viewLifecycleOwner, Observer {
            println("size all news: ${it[1].title}")
        })

        viewModel.getWeeklyNews(MOVIE_NEWS).observe(viewLifecycleOwner, Observer {
            println("movie news: ${it[0].title}")
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it == false) progressBar?.hide()
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
