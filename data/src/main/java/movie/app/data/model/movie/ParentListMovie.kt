package movie.app.data.model.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParentListMovie (
    var title: String,
    var category: String,
    var movieList: List<SmallMovieList>
): Parcelable