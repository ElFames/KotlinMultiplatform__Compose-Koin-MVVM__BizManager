package application.clients.ui.myclients.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import application.clients.domain.models.Client

@Composable
fun ClientInfoContainer(containerColor: Color, client: Client, navigateToDetailScreen: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                navigateToDetailScreen()
            },
        elevation = 4.dp,
        backgroundColor = containerColor,
        contentColor = Color.Black,
        border = BorderStroke(width = 2.dp, color = Color.Gray),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Client Icon",
                    modifier = Modifier.size(22.dp).padding(end = 5.dp)
                )
                Text(
                    text = client.name,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            }


            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Phone Icon",
                    modifier = Modifier.size(20.dp).padding(end = 5.dp)
                )
                Text(
                    text = client.phone,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            }



            Text(
                text = "Nº Compras: ${client.purchases.size}",
                fontFamily = FontFamily.Serif,
                color = Color.LightGray
            )
        }

    }
}