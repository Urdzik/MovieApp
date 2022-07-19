package movie.app.main.presentation.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import movie.app.main.presentation.ui.nav.Screens.Container


@Composable
fun NavHost(navController: NavHostController) =
    NavHost(navController = navController, startDestination = Container.OVERVIEW_CONTAINER) {
        overviewNavGraph(navController)
        searchNavGraph(navController)
        profileNavGraph(navController)
    }


sealed class Screens {
    object Base : Screens() {
        const val OVERVIEW = "overview"
        const val PROFILE = "profile"
        const val SEARCH = "search"
    }

    object Container : Screens() {
        const val OVERVIEW_CONTAINER = "overview_container"
        const val PROFILE_CONTAINER = "profile_container"
        const val SEARCH_CONTAINER = "search_container"
    }
}

