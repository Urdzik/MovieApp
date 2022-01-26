package com.example.movieapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.model.network.data.search.SearchItem
import com.example.movieapp.utils.adapters.SearchAdapter
import com.jakewharton.rxbinding.widget.RxTextView
import dagger.hilt.android.AndroidEntryPoint
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    lateinit var binding: SearchFragmentBinding
    lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = SearchFragmentBinding.inflate(inflater)
        adapter = SearchAdapter(SearchAdapter.ClickListener {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailFragment2(
                    it.id
                )
            )
        })
        with(binding.searchRv) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SearchFragment.adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RxTextView.textChanges(binding.edit)
            .subscribeOn(AndroidSchedulers.mainThread())
            .filter {
                it.length > 2
            }
            .debounce(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { showProgress() }
            .observeOn(Schedulers.io())
            .switchMap {
                apiRequest(it).toObservable()
            }
            .map { resp ->
                resp
                    .filterNot { it.name == null || it.name == "null" }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("response", "result: $it")
                hideProgress()
                adapter.submitList(it)
            }, {
                Log.d("response", "error: $it")
            })
    }

    private fun apiRequest(chars: CharSequence): Single<List<SearchItem>> {
        return viewModel.getSearchResult(chars.toString())
    }

    private fun showProgress() {
//        searchProgressBar.visibility = View.VISIBLE
//        search_rv.visibility = View.INVISIBLE
    }

    private fun hideProgress() {
//        searchProgressBar.visibility = View.INVISIBLE
//        search_rv.visibility = View.VISIBLE
    }
}
