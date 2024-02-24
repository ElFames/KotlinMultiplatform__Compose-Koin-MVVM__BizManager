package application.clients.ui.myclients.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import infrastructure.utils.values.buttonColor

@Composable
fun ClientRow(i: Int, client: Pair<String, String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Text(text = "${i + 1}   ", color = buttonColor, fontFamily = FontFamily.Serif)
            Text(text = client.first, fontFamily = FontFamily.Serif)
        }
        item { Text(text = "${client.second}€", fontFamily = FontFamily.Serif) }
    }
}
