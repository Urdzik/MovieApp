package com.example.movieapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.utils.addFragment
import kotlinx.android.synthetic.main.activity_all_news.*

class AllNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_news)

        addFragment(R.id.container, NewsFragment.newInstance())
    }
}