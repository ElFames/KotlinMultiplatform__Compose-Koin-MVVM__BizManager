package infrastructure.utils

import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BasicAlertDialog(icon: ImageVector, title: String, body: String, color: Color, onConfirmation: () -> Unit) {
    AlertDialog(
        title = {
            Icon(imageVector = icon, contentDescription = "icon", tint = color)
            Text(text = title, color = color)
        },
        text = {
            Text(text = body, color = Color.Black)
        },
        onDismissRequest = {
            onConfirmation()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = "OK", color = Color.Black)
            }
        }
    )
}