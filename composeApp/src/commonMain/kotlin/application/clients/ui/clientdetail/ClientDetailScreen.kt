package application.clients.ui.clientdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import application.clients.ui.clientdetail.components.ClientDataContainer
import application.clients.ui.clientdetail.components.ClientDetailTopToolbar
import application.clients.ui.clientdetail.components.LastPurchases
import domain.models.UiState
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.ScreenTitleWithBackButton
import infrastructure.utils.sharedcomponents.ShowLoadingView
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ClientDetailScreen(
    viewModel: ClientDetailViewModel,
    navigator: Navigator,
    clientId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        UiState.IDLE -> viewModel.getClient(clientId)
        UiState.LOADING -> ShowLoadingView()
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido cargar el cliente",
            color = Color.Red,
            onConfirmation = { navigator.goBack() }
        )

        UiState.SUCCESS -> ShowClientDetailScreen(viewModel, navigator)
    }
}

@Composable
fun ShowClientDetailScreen(
    viewModel: ClientDetailViewModel,
    navController: Navigator
) {
    val client by viewModel.client.collectAsStateWithLifecycle()
    client?.let { viewModel.setLastClientViewed(client!!) }

    Card(
        backgroundColor = Color.LightGray,
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top
        ) {
            ScreenTitleWithBackButton(
                title = "Detalles de ${client?.name}",
                navController
            )
            ClientDetailTopToolbar(client, navController)
            ClientDataContainer(client)
            LastPurchases(client)
        }
    }
}

