package application.auth.ui.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import infrastructure.navigation.AppScreens
import infrastructure.utils.buttonColor
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun RememberRegisterLabel(navController: Navigator) {
    TextButton(
        onClick = { navController.navigate(AppScreens.RegisterScreen.route) },
    ) {
        Text(
            text = "No tienes cuenta todavía? Registrate",
            color = buttonColor,
            fontFamily = FontFamily.Serif
        )
    }
}