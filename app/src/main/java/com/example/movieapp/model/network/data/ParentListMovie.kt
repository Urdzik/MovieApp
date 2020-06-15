package com.example.movieapp.model.network.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParentListMovie (
    var title: String,
    var category: String,
    var movieList: List<SmallMovieList>
): Parcelable