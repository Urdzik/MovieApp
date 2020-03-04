package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Function for Light-Dark theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode())

//        val myNavHostFragment: NavHostFragment = nav_host_fragment as NavHostFragment
//        val inflater = myNavHostFragment.navController.navInflater
//        val graph = inflater.inflate(R.navigation.navigation)
//        myNavHostFragment.navController.graph = graph
    }
}