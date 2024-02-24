package application.auth.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import application.auth.ui.auth.components.HeaderImage
import application.auth.ui.auth.components.NavigateLoginScreenButton
import application.auth.ui.auth.components.NavigateRegisterScreenButton
import infrastructure.utils.values.authBackgroundColor
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun AuthScreen(navController: Navigator) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(color = authBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item { HeaderImage() }
        item {
            NavigateLoginScreenButton(navController)
            NavigateRegisterScreenButton(navController)
        }
    }
}