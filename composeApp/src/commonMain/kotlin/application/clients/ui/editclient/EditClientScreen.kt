package application.clients.ui.editclient

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import domain.models.UiState
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.ScreenTitleWithBackButton
import infrastructure.utils.sharedcomponents.ShowLoadingView
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun EditClientScreen(
    viewModel: EditClientViewModel,
    navigator: Navigator,
    clientId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState) {
        UiState.IDLE -> viewModel.getClient(clientId)
        UiState.LOADING -> ShowLoadingView()
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido cargar el cliente",
            color = Color.Red,
            onConfirmation = { navigator.goBack() }
        )
        UiState.SUCCESS -> ShowEditClientScreen(viewModel, navigator)
    }
}

@Composable
fun ShowEditClientScreen(viewModel: EditClientViewModel, navigator: Navigator) {
    ScreenTitleWithBackButton(title = "Editar Información", navController = navigator)
}
