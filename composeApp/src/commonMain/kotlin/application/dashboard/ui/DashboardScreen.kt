package application.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.models.UiState
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import application.dashboard.ui.components.FilterDashboardBar
import application.dashboard.ui.components.ShowStatistics
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.HorizontalLine
import infrastructure.utils.sharedcomponents.InsertTitle
import infrastructure.utils.sharedcomponents.ShowLoadingView

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> viewModel.updateFilterStatistics(Filter.DIA)
        UiState.LOADING -> {
            ShowLoadingView()
        }
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No se pueden cargar las estadísticas en estos momentos.",
            color = Color.Red,
            onConfirmation = { viewModel.hideError() }
        )
        UiState.SUCCESS -> ShowDashboardScreen(viewModel = viewModel)
    }
}

@Composable
fun ShowDashboardScreen(viewModel: DashboardViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(bottom = 70.dp)
    ) {
        InsertTitle("Panel de Estadísticas")
        HorizontalLine(color = Color.LightGray)
        FilterDashboardBar(viewModel)
        ShowStatistics(viewModel)
    }
}

