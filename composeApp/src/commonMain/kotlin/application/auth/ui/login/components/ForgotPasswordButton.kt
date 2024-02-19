package application.auth.ui.login.components

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

@Composable
fun ForgotPasswordButton() {
    TextButton(onClick = {}) {
        Text(
            text = "Tienes problemas para acceder? Pulsa aqui",
            color = Color.White,
            fontFamily = FontFamily.Serif
        )
    }
}