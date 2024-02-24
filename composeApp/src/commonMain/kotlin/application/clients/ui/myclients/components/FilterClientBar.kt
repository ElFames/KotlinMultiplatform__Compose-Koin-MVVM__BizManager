package application.clients.ui.myclients.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import infrastructure.navigation.AppScreens
import infrastructure.utils.values.buttonColor
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun FilterClientBar(
    showRanking: Boolean,
    clientToSearch: String,
    navController: Navigator,
    onUserToSearchChange: (String) -> Unit,
    changeShowRankingState: () -> Unit,
    getRankingClients: () -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item { ClientSearcher(clientToSearch, onUserToSearchChange) }
        item {
            ShowRankingButton(showRanking, { changeShowRankingState() }, { getRankingClients() })
            AddClientButton(navController)
        }
    }
}

@Composable
private fun ShowRankingButton(showRanking: Boolean, changeShowRankingState: () -> Unit, getRankingClients: () -> Unit) {
    val containerColor = if (showRanking) buttonColor else Color.Gray

    Card(
        modifier = Modifier.padding(13.dp),
        backgroundColor = containerColor,
        shape = CircleShape
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(27.dp)
                .clickable {
                    changeShowRankingState()
                    getRankingClients()
                },
            imageVector = Icons.Default.List,
            contentDescription = "info icon"
        )
    }
}

@Composable
private fun AddClientButton(navController: Navigator) {
    Card(
        modifier = Modifier.padding(13.dp),
        backgroundColor = Color.Gray,
        shape = CircleShape
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(27.dp)
                .clickable { navController.navigate(AppScreens.NewClientScreen.route) },
            imageVector = Icons.Default.AddCircle,
            contentDescription = "info icon"
        )
    }
}