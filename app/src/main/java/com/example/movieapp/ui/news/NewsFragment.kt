package com.example.movieapp.ui.news


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.NewsFragmentBinding

class NewsFragment : Fragment() {


    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = NewsFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
    return binding.root

    }



}
