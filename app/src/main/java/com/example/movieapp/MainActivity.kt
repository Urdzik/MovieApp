package com.example.movieapp


import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import com.example.movieapp.utils.MainScreen
import com.example.movieapp.utils.adapters.MainPagerAdapter
import com.example.movieapp.utils.getMainScreenForMenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//Function for Light-Dark theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode())

        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)

        mainPagerAdapter.setItems(
            arrayListOf(
                MainScreen.OVERVIEW,
                MainScreen.NEWS,
                MainScreen.SEARCH,
                MainScreen.SAVE,
                MainScreen.PROFILE
            )
        )

        val defaultScreen = MainScreen.OVERVIEW
        scrollToScreen(defaultScreen)
        selectBottomNavigationViewMenuItem(defaultScreen.menuItemId)

        // Set the listener for item selection in the bottom navigation view.
        bottom_navigation.setOnNavigationItemSelectedListener(this)

        // Attach an adapter to the view pager and make it select the bottom navigation
        // menu item and change the title to proper values when selected.
        view_pager.adapter = mainPagerAdapter

        view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val selectedScreen = mainPagerAdapter.getItems()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
            }
        })


//        val myNavHostFragment: NavHostFragment = nav_host_fragment as NavHostFragment
//        val inflater = myNavHostFragment.navController.navInflater
//        val graph = inflater.inflate(R.navigation.navigation)
//        myNavHostFragment.navController.graph = graph


    }

    private fun scrollToScreen(mainScreen: MainScreen) {
        val screenPosition = mainPagerAdapter.getItems().indexOf(mainScreen)
        if (screenPosition != view_pager.currentItem) {
            view_pager.currentItem = screenPosition
        }
    }

    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {

        bottom_navigation.setOnNavigationItemSelectedListener(null)
        bottom_navigation.selectedItemId = menuItemId
        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    /**
     * Listener implementation for registering bottom navigation clicks.
     */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        getMainScreenForMenuItem(menuItem.itemId)?.let {
            scrollToScreen(it)
            return true
        }
        return false
    }


}
