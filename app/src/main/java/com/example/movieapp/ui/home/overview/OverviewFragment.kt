package com.example.movieapp.ui.home.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.OverviewFragmentBinding
import com.example.movieapp.ui.home.overview.adapter.OverviewAdapter
import com.example.movieapp.utils.injectViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class OverviewFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: OverviewViewModel
    lateinit var binding: OverviewFragmentBinding
    lateinit var adapter: OverviewAdapter

    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = OverviewFragmentBinding.inflate(inflater)
        viewModel = injectViewModel(viewModelFactory)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.mainRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter =
            OverviewAdapter(
                viewModel
            )
        binding.mainRv.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.parentListMovie.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        //Navigate to Detail Activity
        viewModel.navigateToSelectProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(
                        it.id
                    )
                )
                viewModel.displayPropertyDetailsCompleted()
            }
        })

        //Looking for the internet connection
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
            if (it) onNetworkError()
        })
    }

    //Function will show a toast when there is no internet
    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            errorSnackbar = Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
            errorSnackbar?.show()
        }
    }

}