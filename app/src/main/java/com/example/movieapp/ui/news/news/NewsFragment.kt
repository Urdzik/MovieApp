package com.example.movieapp.ui.news.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.movieapp.databinding.NewsFragmentBinding

class NewsFragment : Fragment() {



    private lateinit var binding: NewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = NewsFragmentBinding.inflate(inflater)



        return binding.root
    }


}