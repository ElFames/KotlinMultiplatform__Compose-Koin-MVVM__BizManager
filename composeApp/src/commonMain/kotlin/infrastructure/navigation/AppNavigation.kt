package infrastructure.navigation

import androidx.compose.runtime.Composable
import application.auth.ui.auth.AuthScreen
import application.auth.ui.login.LoginScreen
import application.auth.ui.login.LoginViewModel
import application.auth.ui.register.RegisterScreen
import application.auth.ui.register.RegisterViewModel
import application.clients.ui.clientdetail.ClientDetailScreen
import application.clients.ui.clientdetail.ClientDetailViewModel
import application.clients.ui.editclient.EditClientScreen
import application.clients.ui.editclient.EditClientViewModel
import application.clients.ui.myclients.MyClientsScreen
import application.clients.ui.myclients.MyClientsViewModel
import application.clients.ui.newclient.NewClientScreen
import application.clients.ui.newclient.NewClientViewModel
import application.dashboard.ui.DashboardScreen
import application.dashboard.ui.DashboardViewModel
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun AppNavigation(navigator: Navigator) {
    NavHost(
        navigator = navigator,
        navTransition = myNavTransitions(),
        initialRoute = AppScreens.AuthScreen.route,
    ) {
        /** AUTH SCREENS **/
        // First Screen
        scene(route = AppScreens.AuthScreen.route, navTransition = myNavTransitions()) {
            AuthScreen(navigator)
        }
        // Login Screen
        scene(route = AppScreens.LoginScreen.route, navTransition = myNavTransitions()) {
            val viewModel = koinViewModel(vmClass = LoginViewModel::class)
            LoginScreen(viewModel, navigator) { navigator.navigate(BottomBarScreens.DashboardScreen.route) }
        }
        // Register Screen
        scene(route = AppScreens.RegisterScreen.route, navTransition = myNavTransitions()) {
            val viewModel = koinViewModel(vmClass = RegisterViewModel::class)
            RegisterScreen(viewModel, navigator)
        }

        /** CLIENT SCREENS **/
        // My Clients Screen
        scene(route = BottomBarScreens.MyClientsScreen.route) {
            val viewModel = koinViewModel(vmClass = MyClientsViewModel::class)
            MyClientsScreen(viewModel, navigator) { navigator.navigate(AppScreens.ClientDetailScreen.route) }
        }
        // Client Detail Screen
        scene(route = AppScreens.ClientDetailScreen.route) {
            val viewModel = koinViewModel(vmClass = ClientDetailViewModel::class)
            ClientDetailScreen(viewModel, navigator, IDs.clientId)
        }

        // Edit Client Screen
        scene(route = AppScreens.EditClientScreen.route) {
            val viewModel = koinViewModel(vmClass = EditClientViewModel::class)
            EditClientScreen(viewModel, navigator, IDs.clientId)
        }
        // New Client Screen
        scene(route = AppScreens.NewClientScreen.route) {
            val viewModel = koinViewModel(vmClass = NewClientViewModel::class)
            NewClientScreen(viewModel, navigator)
        }

        /** DASHBOARD SCREEN **/
        scene(route = BottomBarScreens.DashboardScreen.route) {
            val viewModel = koinViewModel(vmClass = DashboardViewModel::class)
            DashboardScreen(viewModel)
        }
        /** PRODUCTS SCREEN **/
        scene(route = BottomBarScreens.ProductsScreen.route) {

        }
        /** TPV SCREEN **/
        scene(route = BottomBarScreens.TpvPosScreen.route) {

        }
        /** SETTINGS SCREEN **/
        scene(route = BottomBarScreens.SettingsScreen.route) {

        }
    }
}

object IDs {
    var clientId = "1"
    var productId = "productId"
}