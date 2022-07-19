package movie.app.main.presentation.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import movie.app.main.presentation.ui.screens.profile.ProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(startDestination = Screens.Base.PROFILE, route = Screens.Container.PROFILE_CONTAINER) {
        composable(Screens.Base.PROFILE) {
            ProfileScreen()
        }
    }
}
