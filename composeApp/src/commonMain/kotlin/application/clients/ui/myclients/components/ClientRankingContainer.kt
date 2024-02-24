package application.clients.ui.myclients.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infrastructure.utils.values.buttonColor

@Composable
fun ClientRankingContainer(clientRanking: List<Pair<String, String>>) {
    Card(
        modifier = Modifier.padding(16.dp),
        backgroundColor = Color.DarkGray,
        contentColor = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.Gray),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = "Ranking Mejores Clientes",
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                color = buttonColor,
                modifier = Modifier.padding(16.dp, 8.dp)
            )
            if(clientRanking.isNotEmpty()) {
                clientRanking.subList(0,5).forEachIndexed { i, client ->
                    ClientRow(i, client)
                }
            }
            Spacer(modifier = Modifier.height(7.dp))
        }
    }

}