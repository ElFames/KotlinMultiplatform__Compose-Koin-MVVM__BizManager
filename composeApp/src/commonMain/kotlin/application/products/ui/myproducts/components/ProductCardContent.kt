package application.products.ui.myproducts.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import application.products.domain.models.Product
import infrastructure.utils.values.BoldText
import infrastructure.utils.values.Formats
import infrastructure.utils.values.RegularText

@Composable
fun ProductCardContent(product: Product) {
    if (product.imageUrl != null && product.imageUrl != "") {
        // image found
    } else {
        // image not found
    }
    Spacer(modifier = Modifier.height(10.dp))
    BoldText(text = product.name)
    Spacer(modifier = Modifier.height(5.dp))
    RegularText(text = "${Formats.price(product.sellPrice)} €")
}