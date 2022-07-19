package movie.app.main.presentation.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.searchNavGraph(navController: NavHostController) {
    navigation(startDestination = Screens.Base.SEARCH, route = Screens.Container.SEARCH_CONTAINER) {
        composable(Screens.Base.SEARCH) {

        }
    }
}
