package application.products.ui.myproducts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import application.products.ui.myproducts.components.ProductCard
import domain.models.UiState
import infrastructure.navigation.AppScreens
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.HorizontalLine
import infrastructure.utils.sharedcomponents.InsertTitle
import infrastructure.utils.sharedcomponents.ShowLoadingView
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun MyProductsScreen(viewModel: MyProductsViewModel, navController: Navigator) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> viewModel.loadProducts()
        UiState.LOADING -> {
            ShowProductsScreen(viewModel = viewModel, navController = navController)
            ShowLoadingView()
        }
        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error",
            body = "No hay conexión con el servidor",
            color = Color.Red,
            onConfirmation = { navController.popBackStack() }
        )
        UiState.SUCCESS -> ShowProductsScreen(viewModel = viewModel, navController)
    }
}

@Composable
fun ShowProductsScreen(viewModel: MyProductsViewModel, navController: Navigator) {
    val products by viewModel.products.collectAsStateWithLifecycle(initial = mutableListOf())

    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(bottom = 70.dp)
    ) {
        InsertTitle("Lista de Productos")
        HorizontalLine(color = Color.Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.DarkGray),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AddProductButton(navController)
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(230.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            products.forEach { product ->
                item {
                    ProductCard(product = product, navController = navController)
                }
            }
        }
    }
}

@Composable
fun AddProductButton(navController: Navigator) {
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
                .clickable { navController.navigate(AppScreens.NewProductScreen.route) },
            imageVector = Icons.Default.AddCircle,
            contentDescription = "info icon"
        )
    }
}

