package application.auth.ui.register.components

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
fun RememberLoginLabel(navigator: Navigator) {
    TextButton(
        modifier = Modifier.padding(0.dp),
        onClick = {
            navigator.navigate(AppScreens.LoginScreen.route)
        }
    ) {
        Text(
            text = "Ya estás registrado? Accede a tu cuenta",
            color = buttonColor,
            fontFamily = FontFamily.Serif
        )
    }
}