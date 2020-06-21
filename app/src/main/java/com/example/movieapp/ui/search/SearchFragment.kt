package com.example.movieapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.model.network.data.search.SearchItem
import com.example.movieapp.utils.adapters.SearchAdapter
import com.example.movieapp.utils.injectViewModel
import com.example.movieapp.utils.toFlowable
import com.example.movieapp.utils.withParams
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: SearchViewModel
    lateinit var binding: SearchFragmentBinding
    lateinit var adapter: SearchAdapter
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = injectViewModel(viewModelFactory)
        binding = SearchFragmentBinding.inflate(inflater)
        adapter = SearchAdapter(SearchAdapter.ClickListener {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailFragment2(
                    it.id
                )
            )
        })
        binding.lifecycleOwner = viewLifecycleOwner
        with(binding.searchRv) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SearchFragment.adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edit.toFlowable()
            .withParams(
                minLength = 3,
                debounce = 300,
                switchMapper = ::apiRequest,
                doOnNext = ::showProgress
            )
            .map { resp ->
                resp.filterNot { it.name.isNullOrEmpty() || it.name == "null" }
            }
            .subscribe({
                hideProgress()
                adapter.submitList(it)
            }, {
                Log.d("response", "error: $it")
            }
            ).let(disposables::add)
    }

    private fun apiRequest(chars: CharSequence): Flowable<List<SearchItem>> {
        return viewModel.getSearchResult(chars.toString()).toFlowable()
    }

    private fun showProgress() {
        searchProgressBar.visibility = View.VISIBLE
        search_rv.visibility = View.INVISIBLE
    }

    private fun hideProgress() {
        searchProgressBar.visibility = View.INVISIBLE
        search_rv.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
