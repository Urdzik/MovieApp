package com.example.movieapp.utils.viewPager

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.ui.news.NewsFragment
import com.example.movieapp.ui.overview.OverviewFragment
import com.example.movieapp.ui.profile.ProfileFragment
import com.example.movieapp.ui.save.SaveMovieFragment
import com.example.movieapp.ui.search.SearchFragment

enum class MainScreen(@IdRes val menuItemId: Int,
                      @DrawableRes val menuItemIconId: Int,
                      @StringRes val titleStringId: Int,
                      val fragment: Fragment
) {
    OVERVIEW(R.id.overviewFragment, R.drawable.ic_public_black_24dp, R.string.home, OverviewFragment()),
    NEWS(R.id.newsFragment, R.drawable.ic_location_city_black_24dp, R.string.news, NewsFragment()),
    SEARCH(R.id.searchFragment, R.drawable.ic_search_black_24dp, R.string.search, SearchFragment()),
    SAVE(R.id.saveMovieFragment, R.drawable.ic_save_black_24dp, R.string.save, SaveMovieFragment()),
    PROFILE(R.id.profileFragment, R.drawable.ic_person_black_24dp, R.string.profile, ProfileFragment())
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}