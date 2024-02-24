package application.clients.ui.newclient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import domain.models.UiState
import application.clients.ui.newclient.components.textfields.ClientEmailTextField
import application.clients.ui.newclient.components.textfields.ClientNameTextField
import application.clients.ui.newclient.components.SaveButton
import application.clients.ui.newclient.components.textfields.ClientAddressTextField
import application.clients.ui.newclient.components.textfields.ClientPhoneTextField
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.BackScreenButton
import infrastructure.utils.sharedcomponents.ScreenTitleWithBackButton
import infrastructure.utils.sharedcomponents.ShowLoadingView
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun NewClientScreen(viewModel: NewClientViewModel, navController: Navigator) {
    val uiState: UiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState) {
        UiState.IDLE -> ShowNewClientScreen(viewModel, navController)
        UiState.LOADING -> ShowLoadingView()
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "Ha ocurrido un error al añadir el cliente",
            color = Color.Red,
            onConfirmation = { viewModel.finishInsert() }
        )
        UiState.SUCCESS -> {
            BasicAlertDialog(
                icon = Icons.Default.Warning,
                title = "Éxito",
                body = "Cliente añadido con éxito",
                color = Color.Green,
                onConfirmation = {
                    viewModel.finishInsert()
                    navController.goBack()
                }
            )
        }
    }
}

@Composable
fun ShowNewClientScreen(viewModel: NewClientViewModel, navigator: Navigator) {
    val insertEnable by viewModel.insertEnable.collectAsStateWithLifecycle(initial = false)
    val name by viewModel.name.collectAsStateWithLifecycle(initial = "")
    val email by viewModel.email.collectAsStateWithLifecycle(initial = "")
    val phone by viewModel.phone.collectAsStateWithLifecycle(initial = "")
    val address by viewModel.address.collectAsStateWithLifecycle(initial = "")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { ScreenTitleWithBackButton("Nuevo Cliente", navigator) }
        item {
            ClientNameTextField(name) { viewModel.onInsertChange(it, email, phone, address) }
            ClientEmailTextField(email) { viewModel.onInsertChange(name, it, phone, address) }
            ClientPhoneTextField(phone) { viewModel.onInsertChange(name, email, it, address) }
            ClientAddressTextField(address) { viewModel.onInsertChange(name, email, phone, it) }
        }
        item {
            SaveButton(insertEnable) { viewModel.onSaveClient(name, email, phone, address) }
        }
    }
    BackScreenButton(navigator, Color.Black)
}




