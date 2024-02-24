package application.clients.ui.newclient.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import infrastructure.utils.values.buttonColor

@Composable
fun SaveButton(insertEnable: Boolean, onSaveClient: () -> Unit) {
    Button(modifier = Modifier.padding(16.dp),
        onClick = { onSaveClient() },
        enabled = insertEnable,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            contentColor = Color.White,
            disabledBackgroundColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Text(text = "Guardar")
    }
}