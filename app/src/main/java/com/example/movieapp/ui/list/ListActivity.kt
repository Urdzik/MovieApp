package com.example.movieapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.ActivityListBinding
import com.example.movieapp.ui.detail.DetailActivity
import com.example.movieapp.utils.adapters.ListMovieAdapter
import javax.inject.Inject


class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var listViewModelFactory: ViewModelFactory

    lateinit var viewModel: ListViewModel
    lateinit var binding: ActivityListBinding
    private lateinit var layoutManager: GridLayoutManager
    private var popularMoviesPage = 1
    lateinit var movieCategory: String
    lateinit var adapter: ListMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        viewModel = ViewModelProvider(this, listViewModelFactory).get(ListViewModel::class.java)

        movieCategory = intent.getStringExtra("category")
        sendNewMovieList(movieCategory)

        val myToolbar = binding.toolbar

        myToolbar.apply {
            setSupportActionBar(myToolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
            //Button of back
            setNavigationOnClickListener {
                super.onBackPressed()
            }
        }

        binding.recyclerList.adapter = ListMovieAdapter(ListMovieAdapter.ClickListener {
            viewModel.displayPropertyDetails(it)
        }, mutableListOf())

        adapter = binding.recyclerList.adapter as ListMovieAdapter

        layoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false)
        binding.recyclerList.layoutManager = layoutManager
        viewModel.playList.observe(this, Observer {
            adapter.appendMovies(it)
        })

        viewModel.navigateToSelectProperty.observe(this, Observer {
            it?.let {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("movie", it.id)
                startActivity(intent)
                viewModel.displayPropertyDetailsCompleted()
            }
        })

        binding.lifecycleOwner = this
    }

    fun sendNewMovieList(category: String) {
        viewModel.getMovieList(category, popularMoviesPage)
        attachPopularMoviesOnScrollListener()
    }


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

}
