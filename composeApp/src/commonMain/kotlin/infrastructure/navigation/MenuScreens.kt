package infrastructure.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MenuScreens(val route: String, val title: String, val icon: ImageVector) {
    data object DashboardScreen: MenuScreens(route = "dashboard", title = "Dashboard", icon = Icons.Outlined.Info)
    data object ClientsScreen: MenuScreens(route = "clients", title = "Clientes", icon = Icons.Outlined.Person)
    data object TpvPosScreen: MenuScreens(route = "tpv_pos", title = "TPV", icon = Icons.Outlined.ShoppingCart)
    data object ProductsScreen: MenuScreens(route = "products", title = "Productos", icon = Icons.Outlined.Home)
    data object SettingsScreen: MenuScreens(route = "settings", title = "Avanzado", icon = Icons.Outlined.Settings)
}