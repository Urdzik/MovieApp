package com.example.movieapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//Function for Light-Dark theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode())



        val myNavHostFragment: NavHostFragment = nav_host_fragment as NavHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation)
        myNavHostFragment.navController.graph = graph

        NavigationUI.setupWithNavController(bottom_navigation, myNavHostFragment.navController);


    }


    }





