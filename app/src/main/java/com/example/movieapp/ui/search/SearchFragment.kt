package com.example.movieapp.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.ui.home.detail.DetailViewModel
import com.jakewharton.rxbinding.widget.RxTextView
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        binding = SearchFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RxTextView.textChanges(binding.edit)
            .filter {
                it.length > 2
            }
            .debounce(300, TimeUnit.MILLISECONDS)
            .doOnNext {
                //TODO("show progress")
            }
            .observeOn(Schedulers.io())
            .switchMap {
                apiRequest(it).toObservable()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("response", "result: $it")
                binding.result.text = it.joinToString(separator = "\n") { s -> s }
            }, {
                Log.d("response", "error: $it")
            })
    }

    fun apiRequest(chars: CharSequence): Single<List<String>> {
        val data = listOf(
            "Migos",
            "Travis Scott",
            "21 Savage",
            "Lil Peep",
            "Lil Skies",
            "Drake",
            "Gunna",
            "Young Thug",
            "Tyga"
        )

        val response = data.filter { it.contains(chars, true) }
        return Single.just(response)
    }
}
