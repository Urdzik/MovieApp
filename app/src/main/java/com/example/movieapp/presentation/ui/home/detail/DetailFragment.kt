package com.example.movieapp.presentation.ui.home.detail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.databinding.DetailFragmentBinding
import com.example.movieapp.presentation.utils.SHARED_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

   private val viewModel: DetailViewModel by viewModels()
    lateinit var binding: DetailFragmentBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater)

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
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}



