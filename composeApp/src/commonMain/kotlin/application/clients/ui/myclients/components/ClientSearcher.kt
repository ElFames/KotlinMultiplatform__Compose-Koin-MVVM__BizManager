package application.clients.ui.myclients.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import infrastructure.utils.values.buttonColor

@Composable
fun ClientSearcher(clientToSearch: String, onUserToSearchChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .height(76.dp)
            .width(280.dp)
            .padding(12.dp),
        value = clientToSearch,
        onValueChange = { onUserToSearchChange(it) },
        placeholder = { Text(text = "Buscador...") },
        leadingIcon = { Icons.Default.Search },
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.LightGray,
            cursorColor = buttonColor
        )
    )
}
