package application.clients.ui.clientdetail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import application.clients.domain.models.Client
import domain.models.Purchase

@Composable
fun LastPurchases(client: Client?) {
    Text(
        modifier = Modifier.padding(start = 17.dp, top = 25.dp),
        text = "Últimos pedidos",
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        fontSize = 19.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Black
    )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(220.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        client?.purchases?.sortedWith(
            compareBy<Purchase> { it.dateTime.year }
                .thenBy { it.dateTime.month }
                .thenBy { it.dateTime.day }
                .thenBy { it.dateTime.hour }
                .thenBy { it.dateTime.minute }
        )?.forEach { purchase ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.small,
                    backgroundColor = Color.White,
                    contentColor = Color.Black,
                    elevation = 4.dp,
                    border = BorderStroke(2.dp, color = Color.Gray)
                ) {
                    PurchaseInfoContainer(purchase)
                }
            }
        }
    }
}