package application.products.ui.myproducts.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import application.products.domain.models.Product
import infrastructure.navigation.AppScreens
import infrastructure.navigation.IDs
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ProductCard(product: Product, navController: Navigator) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                IDs.productId = product.id
                navController.navigate(route = AppScreens.ProductDetailScreen.route)
            },
        elevation = 4.dp,
        backgroundColor = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.Gray),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProductCardContent(product = product)
        }
    }
}
