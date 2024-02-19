package infrastructure.navigation

import androidx.compose.runtime.Composable
import application.auth.ui.auth.AuthScreen
import application.auth.ui.login.LoginScreen
import application.auth.ui.login.LoginViewModel
import application.auth.ui.register.RegisterScreen
import application.auth.ui.register.RegisterViewModel
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun AppNavigation() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = AppScreens.AuthScreen.route,
    ) {

        /** AUTH SCREENS **/
        // FirstScreen
        scene(
            route = AppScreens.AuthScreen.route,
            navTransition = NavTransition(),
        ) {
            AuthScreen(navigator)
        }
        // LoginScreen
        scene(
            route = AppScreens.LoginScreen.route,
            navTransition = NavTransition(),
        ) {
            val viewModel = koinViewModel(vmClass = LoginViewModel::class)
            LoginScreen(viewModel, navigator) { /* navigateToDashboard */ }
        }
        // RegisterScreen
        scene(
            route = AppScreens.RegisterScreen.route,
            navTransition = NavTransition(),
        ) {
            val viewModel = koinViewModel(vmClass = RegisterViewModel::class)
            RegisterScreen(viewModel, navigator)
        }

    }
}