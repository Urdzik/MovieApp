@file:OptIn(ExperimentalMaterial3Api::class)
package movie.app.main.presentation.ui.screens.overview


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import movie.app.main.BuildConfig

data class CategoryMovieList(
    val name: String,
    val movies: List<Movie>
)

data class Movie(
    val id: Int,
    val url: String,
    val name: String,
    val rating: Float,
)

@Composable
fun CategoryMovieList(
    modifier: Modifier = Modifier,
    data: CategoryMovieList,
    onMovieClick: (Int) -> Unit,
    onDipClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = data.name,
            style = MaterialTheme.typography.titleLarge
        )
        MovieList(list = data.movies, onMovieClick = onMovieClick) {}
    }
}

@Composable
private fun MovieList(
    modifier: Modifier = Modifier,
    list: List<Movie>,
    onMovieClick: (Int) -> Unit,
    onDipClick: () -> Unit
) {
    LazyRow(modifier = modifier) {
        items(
            count = list.size,
            key = { list[it].id },
        ) { index ->
            MovieItem(data = list[index], onClick = onMovieClick)
        }
        item { DipItem(onDipClick) }
    }
}

@Composable
private fun MovieItem(data: Movie, onClick: (Int) -> Unit) {
    Column(Modifier.width(170.dp)) {
        Card(
            modifier = Modifier
                .height(height =  230.dp)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .clickable(onClick = {
                    onClick(data.id)
                }),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation()
        ) {
            val painter = rememberImagePainter(data = "${BuildConfig.BASE_IMAGE_URL}${data.url}")
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = data.name, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun DipItem(onClick: () -> Unit) {

}

