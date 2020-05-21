package com.example.movieapp.ui.home.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.OverviewFragmentBinding
import com.example.movieapp.ui.home.overview.groupie.ParentItem
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject


class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: OverviewViewModel
    lateinit var binding: OverviewFragmentBinding

    private var errorSnackbar: Snackbar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        binding = OverviewFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)

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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        binding.mainRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.mainRv.adapter = GroupAdapter<ViewHolder>().also {
            val item = viewModel.parentListMovie.value?.map { ParentItem(it) }
            if (item != null) {
                it.update(item)
            }
        }
        binding.mainRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        return binding.root
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