package application.auth.ui.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import infrastructure.navigation.AppScreens
import infrastructure.utils.values.authBackgroundColor
import infrastructure.utils.values.buttonColor
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun NavigateRegisterScreenButton(navController: Navigator) {
    Button(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .width(Dp.Infinity)
            .height(60.dp)
            .padding(horizontal = 25.dp, vertical = 7.dp),
        onClick = {
            navController.navigate(AppScreens.RegisterScreen.route)
        },
        border = BorderStroke(1.dp, buttonColor),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = authBackgroundColor,
            contentColor = buttonColor
        )
    ) {
        Text(
            text = "CREAR CUENTA",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    }
}