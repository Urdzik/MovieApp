package com.example.movieapp.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addFragment(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(container, fragment)
        .commitAllowingStateLoss()
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}