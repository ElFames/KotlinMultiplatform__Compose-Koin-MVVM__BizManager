package application.clients.ui.myclients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import application.clients.domain.models.Client
import domain.models.UiState
import application.clients.ui.myclients.components.ClientInfoContainer
import application.clients.ui.myclients.components.ClientRankingContainer
import application.clients.ui.myclients.components.FilterClientBar
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.HorizontalLine
import infrastructure.utils.sharedcomponents.InsertTitle
import infrastructure.utils.sharedcomponents.ShowLoadingView
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun MyClientsScreen(viewModel: MyClientsViewModel, navController: Navigator, navigateToDetailScreen: () -> Unit) {
    val uiState: UiState by viewModel.uiState.collectAsStateWithLifecycle(initial = UiState.IDLE)

    when(uiState) {
        UiState.IDLE -> viewModel.loadClients()
        UiState.LOADING -> ShowLoadingView()
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido recuperar los clientes",
            color = Color.Red,
            onConfirmation = { navController.goBack() }
        )
        UiState.SUCCESS -> ShowMyClientsScreen(viewModel, navController, navigateToDetailScreen)
    }
}

@Composable
fun ShowMyClientsScreen(
    viewModel: MyClientsViewModel,
    navController: Navigator,
    navigateToDetailScreen: () -> Unit
) {
    var showRanking by rememberSaveable { mutableStateOf(false) }
    val clientToSearch by viewModel.clientToSearch.collectAsStateWithLifecycle(initial = "")
    val clients by viewModel.clients.collectAsStateWithLifecycle(initial = mutableListOf())
    val clientRanking by viewModel.clientRanking.collectAsStateWithLifecycle(initial = mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(bottom = 70.dp)
    ) {
        InsertTitle("Lista de Clientes")
        HorizontalLine(color = Color.LightGray)
        FilterClientBar(
            showRanking,
            clientToSearch,
            navController,
            { viewModel.onClientToSearchChange(it) },
            { showRanking = !showRanking },
            { viewModel.getClientRanking() }
        )
        if (showRanking) ClientRankingContainer(clientRanking)
        ListGridClients(clients, navigateToDetailScreen)
    }
}

@Composable
fun ListGridClients(
    clients: List<Client>,
    navigateToDetailScreen: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        userScrollEnabled = true,
    ) {
        clients.forEach { client ->
            item {
                ClientInfoContainer(Color.White, client, navigateToDetailScreen)
            }
        }
    }
}