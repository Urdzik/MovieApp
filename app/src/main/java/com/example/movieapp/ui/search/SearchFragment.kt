package com.example.movieapp.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.model.network.data.search.SearchItem
import com.example.movieapp.utils.adapters.SearchAdapter
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.search_fragment.*
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: SearchViewModel
    lateinit var binding: SearchFragmentBinding
    lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        binding = SearchFragmentBinding.inflate(inflater)
        adapter = SearchAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        with(binding.searchRv) {
            layoutManager = GridLayoutManager(context, 2)
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

    fun apiRequest(chars: CharSequence): Single<List<SearchItem>> {
        return viewModel.getSearchResult(chars.toString())
    }

    fun showProgress() {
        searchProgressBar.visibility = View.VISIBLE
        search_rv.visibility = View.INVISIBLE
    }

    fun hideProgress() {
        searchProgressBar.visibility = View.INVISIBLE
        search_rv.visibility = View.VISIBLE
    }
}
