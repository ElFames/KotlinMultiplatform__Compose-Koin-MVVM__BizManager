package application.auth.ui.register.components

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
import infrastructure.utils.values.buttonColor
import infrastructure.utils.values.disabledButtonColor

@Composable
fun RegisterButton(registerEnable: Boolean, onRegisterSelected: () -> Unit) {
    Button(
        enabled = registerEnable,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .width(Dp.Infinity)
            .height(60.dp)
            .padding(horizontal = 25.dp, vertical = 7.dp),
        onClick = { onRegisterSelected() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            contentColor = Color.White,
            disabledBackgroundColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = "CREAR MI CUENTA",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    }
}