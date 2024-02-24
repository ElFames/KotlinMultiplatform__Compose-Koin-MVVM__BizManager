package application.products.ui.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.models.UiState
import application.products.domain.models.Product
import application.products.domain.models.SubProduct
import infrastructure.navigation.AppScreens
import infrastructure.navigation.IDs
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.HorizontalLine
import infrastructure.utils.sharedcomponents.ScreenTitleWithBackButton
import infrastructure.utils.sharedcomponents.ShowLoadingView
import infrastructure.utils.values.BoldText
import infrastructure.utils.values.Formats
import infrastructure.utils.values.RegularText
import infrastructure.utils.values.buttonColor
import infrastructure.utils.values.disabledButtonColor
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel,
    navController: Navigator,
    productId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> viewModel.getProduct(productId)
        UiState.LOADING -> {
            ShowProductDetailScreen(viewModel, navController)
            ShowLoadingView()
        }

        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error al cargar producto",
            body = "No se ha podido cargar la información del producto",
            color = Color.Red,
            onConfirmation = { navController.popBackStack() }
        )

        UiState.SUCCESS -> ShowProductDetailScreen(viewModel, navController)
    }
}

@Composable
fun ShowProductDetailScreen(viewModel: ProductDetailViewModel, navController: Navigator) {
    val product by viewModel.product.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .scrollable(
                state = ScrollableState { 0f },
                orientation = Orientation.Vertical,
                enabled = true,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ScreenTitleWithBackButton(title = "Detalles del producto", navController)
        ProductInfo(product)
        ProductDescription(product)
        ProductStatistics(product)
        EditProductButton(navController, product?.id?: "")
    }
}

@Composable
fun ProductStatistics(product: Product?) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProductExpenses(product)
        ProductMargins(product)
    }
}

@Composable
fun EditProductButton(navController: Navigator, productId: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            enabled = true,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 25.dp, vertical = 7.dp),
            onClick = {
                IDs.productId = productId
                navController.navigate(AppScreens.EditProductScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor,
                contentColor = Color.White,
                disabledBackgroundColor = disabledButtonColor,
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Editar Producto")
        }
    }
}

@Composable
fun ProductDescription(product: Product?) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
            .background(Color.White, MaterialTheme.shapes.medium),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        RegularText(text = product?.description ?: "")
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
fun ProductInfo(product: Product?) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProductImage(product?.imageUrl)
        product?.let { ProductPriceInfo(product) }
    }
}

@Composable
fun ProductExpenses(product: Product?) {
    HorizontalLine(color = Color.Black)
    BoldText(text = "Costes del producto")
    product?.expenses?.forEach {
        ProductExpenseLine(it)
    }
}

@Composable
fun ProductExpenseLine(it: SubProduct) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(12.dp, 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RegularText(it.quantity.toString())
        RegularText(it.quantityCurrency)
        RegularText(" de ${it.name}")
        Spacer(modifier = Modifier.width(5.dp))
        RegularText("${Formats.price(it.costPrice)}€")
    }
}

@Composable
fun ProductMargins(product: Product?) {
    Spacer(modifier = Modifier.height(15.dp))
    HorizontalLine(color = Color.Black)
    BoldText(text = "Margenes del producto")
    val totalExpenses = product?.expenses?.sumOf { it.costPrice } ?: 0.0
    RegularText(text = "Coste Total: ${Formats.price(totalExpenses)}€")

    val profit = ((product?.sellPrice ?: 0.0) - totalExpenses)
    RegularText(text = "Beneficio: ${Formats.price(profit)}€")

    val percentageProfit = (profit / totalExpenses) * 100
    RegularText(text = "Margen: ${Formats.price(percentageProfit)}%")
}

@Composable
fun ProductPriceInfo(product: Product) {
    Card(
        modifier = Modifier.padding(8.dp),
        backgroundColor = Color.White,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = product.name,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = "${Formats.price(product.sellPrice)}€",
                color = buttonColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Composable
fun ProductImage(imageUrl: String?) {
    if (imageUrl != null && imageUrl != "") {
        // image from url
    } else {
        // image not found
    }
}
