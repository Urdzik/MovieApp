package com.example.movieapp.ui.detail

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.DetailActivityBinding


import javax.inject.Inject


class DetailActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        App.appComponent.detailInject(this)


        val  binding: DetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity)

        //Get movie object

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        // Create Toolbar and button of back in toolbar
        val myToolbar = binding.toolbar

        myToolbar.apply {
            setSupportActionBar(myToolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
//            title = movie.title
        }

        //Button of back
        myToolbar.setNavigationOnClickListener {

        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this



    }
    }



