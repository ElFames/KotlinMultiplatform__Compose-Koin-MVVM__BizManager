package application.auth.ui.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
fun EmailTextField(email: String, onLoginChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onLoginChange(it) },
        placeholder = { Text(text = "Introduce tu email...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .border(border = BorderStroke(1.dp, buttonColor), shape = MaterialTheme.shapes.small)
            .height(52.dp)
            .width(Dp.Infinity),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "email_icon", tint = Color.White)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.White,
            backgroundColor = authBackgroundColor
        ),
    )
}