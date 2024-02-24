package application.dashboard.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import application.dashboard.ui.DashboardViewModel
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle

@Composable
fun ShowStatistics(viewModel: DashboardViewModel) {
    val bestSellers by viewModel.bestSellers.collectAsStateWithLifecycle(initial = mutableListOf())
    val moreProfit by viewModel.moreProfit.collectAsStateWithLifecycle(initial = mutableListOf())
    val income by viewModel.income.collectAsStateWithLifecycle(initial = "")
    val expenses by viewModel.expenses.collectAsStateWithLifecycle(initial = "")
    val profit by viewModel.profit.collectAsStateWithLifecycle(initial = "")
    val numSales by viewModel.numOfSales.collectAsStateWithLifecycle(initial = "")

    LazyVerticalGrid(
        columns = GridCells.Adaptive(170.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        item {
            DashboardCard(
                title = "Ingresos",
                content = listOf(income),
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Gastos",
                content = listOf(expenses),
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Beneficios",
                content = listOf(profit),
                backgroundColor = Color.White
            )
        }
        item {
            DashboardCard(
                title = "Ventas",
                content = listOf(numSales),
                backgroundColor = Color.White
            )
        }
        item {
            val productsBestSeller = bestSellers?.map { it.name }
            DashboardCard(
                title = "Más Vendidos",
                content = productsBestSeller ?: listOf(),
                backgroundColor = Color.White
            )
        }
        item {
            val productsMoreProfit = moreProfit?.map { it.name }
            DashboardCard(
                title = "Más Rentables",
                content = productsMoreProfit ?: listOf(),
                backgroundColor = Color.White
            )
        }

    }
}