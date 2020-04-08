package com.example.movieapp.ui.news

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.GeneralNewsFragmentBinding
import com.example.movieapp.utils.adapters.SeeAllListener
import com.example.movieapp.utils.adapters.TvNewsAdapter
import com.example.movieapp.utils.adapters.WeeklyGeneralNewsAdapter
import com.example.movieapp.utils.hide
import com.example.movieapp.utils.show
import kotlinx.android.synthetic.main.general_news_fragment.*
import javax.inject.Inject

class GeneralNewsFragment : Fragment() {

    companion object {
        fun newInstance() = GeneralNewsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: GeneralNewsViewModel
    private lateinit var binding: GeneralNewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GeneralNewsViewModel::class.java)
        binding = GeneralNewsFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_weekly.adapter = WeeklyGeneralNewsAdapter()
        recycler_tv.adapter = TvNewsAdapter(SeeAllListener {
            viewModel.redirectToAllNews()
        })
        progressBar.show()

        viewModel.weeklyNewsLiveData.observe(viewLifecycleOwner, Observer {
            (recycler_weekly.adapter as WeeklyGeneralNewsAdapter).submitList(it)
        })

        viewModel.tvNewsLiveData.observe(viewLifecycleOwner, Observer {
            (recycler_tv.adapter as TvNewsAdapter).addHeaderAndSubmitList(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it == false) progressBar?.hide()
        })

        viewModel.redirectToAllNewsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(GeneralNewsFragmentDirections.actionGeneralNewsFragmentToNewsFragment())
                viewModel.redirectComplete()
            }
        })

        detailed.setOnClickListener {
            viewModel.redirectToAllNews()
        }
    }
}