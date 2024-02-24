package application.clients.ui.clientdetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import application.clients.domain.models.Client
import infrastructure.navigation.AppScreens
import infrastructure.navigation.BottomBarScreens
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ClientDetailTopToolbar(client: Client?, navigator: Navigator) {
    Row(modifier = Modifier.fillMaxWidth().padding(13.dp,13.dp,13.dp, bottom = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Image(modifier = Modifier
            .border(1.dp, Color.DarkGray, shape = MaterialTheme.shapes.medium)
            .padding(5.dp)
            .clickable { navigator.navigate(AppScreens.EditClientScreen.route + "/${client?.id}") },
            imageVector = Icons.Default.Edit,
            contentDescription = "edit_icon"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(modifier = Modifier
            .border(1.dp, Color.DarkGray, shape = MaterialTheme.shapes.medium)
            .padding(5.dp)
            .clickable { navigator.navigate(BottomBarScreens.TpvPosScreen.route) },
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "init_purchase_icon"
        )
    }
}