package com.example.movieapp.ui.detail


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.DetailActivityBinding
import com.example.movieapp.model.network.NetworkMovie
import com.example.movieapp.utils.logI
import javax.inject.Inject



class DetailActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: DetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity)

        App.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        val movie = intent.getParcelableExtra<NetworkMovie>("movie")




        // Create Toolbar and button of back in toolbar
        val myToolbar = binding.toolbar

        myToolbar.apply {
            setSupportActionBar(myToolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
                setDisplayShowTitleEnabled(false)
            }

        }

        //Button of back
        myToolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


    }
}



