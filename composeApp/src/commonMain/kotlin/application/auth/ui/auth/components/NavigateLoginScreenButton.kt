package application.auth.ui.auth.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import infrastructure.navigation.AppScreens
import infrastructure.utils.values.buttonColor
import infrastructure.utils.values.disabledButtonColor
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun NavigateLoginScreenButton(navController: Navigator) {
    Button(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .height(60.dp)
            .width(Dp.Infinity)
            .padding(horizontal = 25.dp, vertical = 7.dp),
        onClick = {
            navController.navigate(AppScreens.LoginScreen.route)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            contentColor = Color.White,
            disabledBackgroundColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = "INICIAR SESIÓN",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    }
}
