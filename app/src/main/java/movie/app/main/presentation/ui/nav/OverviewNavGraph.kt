package movie.app.main.presentation.ui.nav

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import movie.app.main.presentation.ui.screens.overview.OverviewScreen
import movie.app.main.presentation.ui.screens.overview.OverviewViewModel

fun NavGraphBuilder.overviewNavGraph(navController: NavHostController) {
    navigation(startDestination = Screens.Base.OVERVIEW, route = Screens.Container.OVERVIEW_CONTAINER) {
        composable(Screens.Base.OVERVIEW) {
            val viewModel = hiltViewModel<OverviewViewModel>()
            OverviewScreen(viewModel, navController)
        }
    }
}
