package com.example.movieapp.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.DetailFragmentBinding
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DetailViewModel
    lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.appComponent.inject(this)

        binding = DetailFragmentBinding.inflate(inflater)

        val args = DetailFragmentArgs.fromBundle(requireArguments()).id

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        viewModel.getSelectedMovieById(args)

        binding.imageButton.setOnClickListener {
            Toast.makeText(context, "Save Movie", Toast.LENGTH_SHORT).show()
        }


        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}



