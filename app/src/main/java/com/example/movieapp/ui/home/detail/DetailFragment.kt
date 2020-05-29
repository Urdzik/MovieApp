package com.example.movieapp.ui.home.detail


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.DetailFragmentBinding
import com.example.movieapp.model.network.data.SmallMovieList
import com.example.movieapp.utils.SHARED_KEY
import com.example.movieapp.utils.ioTaskAsync
import com.example.movieapp.utils.startJob
import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DetailViewModel
    lateinit var binding: DetailFragmentBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        binding = DetailFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        val args = DetailFragmentArgs.fromBundle(requireArguments()).id

        sharedPreferences = activity?.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)!!
        val id = sharedPreferences.getString(SHARED_KEY, null)

        if (id != null) {
            viewModel.getUserId(id)
            viewModel.checkForSavedMovie(id)
        }

        viewModel.getSelectedMovieById(args)

        binding.imageButton.setOnClickListener {
            if (id == null || id == "null") {
                Toast.makeText(context, "Please Sing in your account", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                viewModel.putMovieInDatabase()
            }
        }

        viewModel.test.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.imageButton.visibility = View.GONE
            } else {
                binding.imageButton.visibility = View.VISIBLE
            }
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}



