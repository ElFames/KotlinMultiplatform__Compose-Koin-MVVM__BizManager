package infrastructure.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(val route: String, val title: String, val icon: ImageVector) {
    data object DashboardScreen :
        BottomBarScreens(route = "dashboard", title = "Dashboard", icon = Icons.Outlined.Info)

    data object MyClientsScreen :
        BottomBarScreens(route = "myclients", title = "Clientes", icon = Icons.Outlined.Person)

    data object TpvPosScreen :
        BottomBarScreens(route = "tpv_pos", title = "TPV", icon = Icons.Outlined.ShoppingCart)

    data object ProductsScreen :
        BottomBarScreens(route = "products", title = "Productos", icon = Icons.Outlined.Home)

    data object SettingsScreen :
        BottomBarScreens(route = "settings", title = "Avanzado", icon = Icons.Outlined.Settings)

    companion object {
        fun values(): List<BottomBarScreens> {
            return listOf(
                DashboardScreen,
                MyClientsScreen,
                TpvPosScreen,
                ProductsScreen,
                SettingsScreen
            )
        }
    }
}