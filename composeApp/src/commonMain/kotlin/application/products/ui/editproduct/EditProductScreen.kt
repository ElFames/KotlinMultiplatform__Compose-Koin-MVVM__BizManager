package application.products.ui.editproduct

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
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun EditProductScreen(
    viewModel: EditProductViewModel,
    navController: Navigator,
    productId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        UiState.IDLE -> viewModel.getProduct(productId)
        UiState.LOADING -> {
            ShowEditProductScreen(viewModel, navController)
            ShowLoadingView()
        }
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se ha podido cargar el producto",
            color = Color.Red,
            onConfirmation = { navController.goBack() }
        )
        UiState.SUCCESS -> ShowEditProductScreen(viewModel, navController)
    }
}

@Composable
fun ShowEditProductScreen(viewModel: EditProductViewModel, navController: Navigator) {
    ScreenTitleWithBackButton(title = "Editar Información", navController = navController)
}
