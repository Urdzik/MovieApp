package movie.app.main.presentation.ui.screens.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import movie.app.data.model.movie.SmallMovieList
import movie.app.main.presentation.ui.nav.Screens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OverviewScreen(viewModel: OverviewViewModel, navController: NavHostController) {
    val list by viewModel.parentListMovie.collectAsState()
    val pagerState = rememberPagerState()
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.BottomCenter) {
            Column {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (title, avatar) = createRefs()
                    Text(
                        modifier = Modifier.constrainAs(title) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = 16.dp)
                        },
                        text = "Home",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screens.Container.PROFILE_CONTAINER)
                            }
                            .constrainAs(avatar) {
                                top.linkTo(title.top)
                                bottom.linkTo(title.bottom)
                                end.linkTo(parent.end, margin = 16.dp)
                            },
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null,
                    )
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    list.forEach { listMovie ->
                        when (listMovie.category) {
                            "" -> banners(listMovie.movieList, pagerState)
                            else -> item {
                                CategoryMovieList(
                                    data = CategoryMovieList(
                                        listMovie.title,
                                        listMovie.movieList.map {
                                            Movie(
                                                it.id,
                                                it.posterPath,
                                                it.title,
                                                it.voteAverage
                                            )
                                        }), onMovieClick = {}) {

                                }
                            }
                        }
                    }


                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 32.dp),
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                onClick = { navController.navigate(Screens.Base.SEARCH) }) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun LazyListScope.banners(list: List<SmallMovieList>, pagerState: PagerState) {
    list.filterIndexed { index, _ -> index < 5 }
        .map { RecommendationSlider(it.id, it.backdropPath) }
        .let {
            item(key = { Int.MAX_VALUE }) {
                RecommendationSlider(
                    list = it,
                    modifier = Modifier,
                    pagerState = pagerState,
                    onClick = {

                    })
            }
        }
}


