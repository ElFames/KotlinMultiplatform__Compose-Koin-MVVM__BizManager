package application.auth.ui.register.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import infrastructure.utils.values.authBackgroundColor
import infrastructure.utils.values.buttonColor

@Composable
fun ConfirmPasswordTextField(confirmPassword: String, onLoginChange: (String) -> Unit) {
    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { onLoginChange(it) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .border(border = BorderStroke(1.dp, buttonColor), shape = MaterialTheme.shapes.small)
            .width(Dp.Infinity)
            .height(52.dp),
        placeholder = { Text("Confirma la contraseña", color = Color.Gray) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "confirm_password_icon", tint = Color.White)
        },
        singleLine = true,
        keyboardActions = KeyboardActions(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            backgroundColor = authBackgroundColor
        ),
    )
}
