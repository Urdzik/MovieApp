@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)

package movie.app.main.presentation.ui.screens.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import movie.app.main.BuildConfig
import movie.app.main.presentation.ui.theme.AppTheme
import com.google.accompanist.pager.*

data class RecommendationSlider(
    val id: Int,
    val url: String,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecommendationSlider(
    modifier: Modifier = Modifier,
    list: List<RecommendationSlider>,
    onClick: (Int) -> Unit = {},
    pagerState: PagerState
) {

    Surface(modifier = modifier) {
        Column {
            HorizontalPager(
                state = pagerState,
                count = list.size,
                key = { list[it].id }
            ) { index ->
                val item = list[index]
                Item(
                    content = item,
                    index = index,
                    onClick = { onClick(item.id) })
            }
            HorizontalPagerIndicator(
                activeColor = MaterialTheme.colorScheme.onTertiary,
                inactiveColor = MaterialTheme.colorScheme.onTertiaryContainer,
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    content: RecommendationSlider,
    index: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(250.dp)
            .padding(16.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column {
            val painter = rememberImagePainter(data = "${BuildConfig.BASE_IMAGE_URL}${content.url}")
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
private fun RecommendationSliderPreviewWhite() {
    AppTheme(useDarkTheme = false) {
        RecommendationSlider(modifier = Modifier, list = listOf(
            RecommendationSlider(
                id = 0,
                url = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            ),
            RecommendationSlider(
                id = 1,
                url = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            ),
        ), onClick = {}, pagerState = rememberPagerState()
        )
    }
}

@Preview
@Composable
private fun RecommendationSliderPreviewBlack() {
    AppTheme(useDarkTheme = true) {
        RecommendationSlider(modifier = Modifier, list = listOf(
            RecommendationSlider(
                id = 0,
                url = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            ),
            RecommendationSlider(
                id = 1,
                url = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            ),
        ), onClick = {}, pagerState = rememberPagerState()
        )
    }
}


