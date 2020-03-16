package com.example.movieapp.ui.news

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.movieapp.R
import com.example.movieapp.dagger.App
import com.example.movieapp.dagger.module.viewModule.ViewModelFactory
import kotlinx.android.synthetic.main.general_news_fragment.*
import javax.inject.Inject

class GeneralNewsFragment : Fragment() {

    companion object {
        fun newInstance() = GeneralNewsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: GeneralNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GeneralNewsViewModel::class.java)
        return inflater.inflate(R.layout.general_news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.weeklyNewsLiveData.observe(viewLifecycleOwner, Observer {
            text.text = it[0].id.toString()
        })
    }

}
