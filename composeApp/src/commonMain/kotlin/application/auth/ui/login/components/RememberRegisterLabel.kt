package application.auth.ui.login.components

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import infrastructure.navigation.AppScreens
import infrastructure.utils.values.buttonColor
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