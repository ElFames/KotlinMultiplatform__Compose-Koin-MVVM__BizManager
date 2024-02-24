import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import di.InitKoin
import infrastructure.navigation.AppNavigation
import infrastructure.navigation.BottomBarScreens
import infrastructure.utils.values.buttonColor
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun BizManagerApp() {
    PreComposeApp {
        val navigator = rememberNavigator()
        InitKoin()
        MaterialTheme {
            Scaffold(
                bottomBar = { BottomBar(navigator) }
            ) {
                AppNavigation(navigator)
            }
        }
    }
}

@Composable
fun BottomBar(
    navigator: Navigator
) {
    val screens = BottomBarScreens.values()
    val backStackEntry = navigator.currentEntry.collectAsState(initial = null)
    val currentRoute = backStackEntry.value?.route?.route

    val bottomBarDestination = screens.any { it.route == currentRoute }
    if (bottomBarDestination) {
        BottomAppBar(
            modifier = Modifier,
            backgroundColor = Color.DarkGray,
            elevation = 4.dp
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentRoute = currentRoute,
                    navigator = navigator
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentRoute: String?,
    navigator: Navigator
) {
    BottomNavigationItem(
        selected = screen.route == currentRoute,
        icon = { Icon(imageVector = screen.icon, contentDescription = "nav_icon") },
        modifier = Modifier,
        enabled = true,
        label = { Text(text = screen.title) },
        alwaysShowLabel = true,
        onClick = {
            navigator.navigate(
                screen.route,
                NavOptions(
                    popUpTo = PopUpTo(BottomBarScreens.DashboardScreen.route),
                    launchSingleTop = true
                )
            )
        },
        selectedContentColor = buttonColor,
        unselectedContentColor = Color.Gray
    )
}