package application.products.ui.newproducts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import domain.models.UiState
import application.products.domain.models.SubProduct
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.HorizontalLine
import infrastructure.utils.sharedcomponents.ScreenTitleWithBackButton
import infrastructure.utils.sharedcomponents.ShowLoadingView
import infrastructure.utils.values.BoldText
import infrastructure.utils.values.buttonColor
import infrastructure.utils.values.disabledButtonColor
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun NewProductScreen(viewModel: NewProductViewModel, navController: Navigator) {
    val uiState: UiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> ShowNewProductScreen(viewModel, navController)
        UiState.LOADING -> {
            ShowNewProductScreen(viewModel = viewModel, navController = navController)
            ShowLoadingView()
        }

        UiState.ERROR -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Error al añadir producto",
            body = "No hay conexión con el servidor",
            color = Color.Red,
            onConfirmation = { viewModel.finishInsert() }
        )

        UiState.SUCCESS -> BasicAlertDialog(
            icon = Icons.Default.Warning,
            title = "Éxito",
            body = "Producto añadido con éxito",
            color = Color.Red,
            onConfirmation = {
                viewModel.finishInsert()
                navController.goBack()
            }
        )
    }
}

@Composable
fun ShowNewProductScreen(viewModel: NewProductViewModel, navController: Navigator) {
    val insertEnable by viewModel.insertEnable.collectAsState(initial = false)
    /*
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) imageUri = uri
    }
    */

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ScreenTitleWithBackButton("Nuevo Producto", navController)
            PreviewSelectedImage("")
            //ImageSelectorButton(galleryLauncher)
            ProductForm(viewModel)
            OnSaveProductButton(insertEnable, "", viewModel)
        }
    }
}

@Composable
fun ProductNameTextField(productName: String, onInsertChange: (String) -> Unit) {
    OutlinedTextField(
        value = productName,
        onValueChange = { onInsertChange(it) },
        placeholder = { Text(text = "Nombre del producto...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .fillMaxWidth()
            .height(52.dp),
        label = { Text(text = "Nombre") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "productName_icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            backgroundColor = Color.White
        ),
    )
}

@Composable
fun ProductForm(viewModel: NewProductViewModel) {
    val productName by viewModel.productName.collectAsStateWithLifecycle(initial = "")
    val description by viewModel.description.collectAsStateWithLifecycle(initial = "")
    val sellPrice by viewModel.sellPrice.collectAsStateWithLifecycle(initial = 0.0)
    var quantityExpenses by rememberSaveable { mutableIntStateOf(1) }

    Spacer(modifier = Modifier.height(20.dp))
    HorizontalLine(color = Color.DarkGray)
    BoldText(text = "Datos del producto")
    ProductNameTextField(productName) { viewModel.onInsertChange(it, description, sellPrice) }
    ProductDescriptionTextField(description) { viewModel.onInsertChange(productName, it, sellPrice) }
    ProductPriceTextField(sellPrice) { viewModel.onInsertChange(productName, description, it) }
    Spacer(modifier = Modifier.height(20.dp))

    HorizontalLine(color = Color.DarkGray)
    BoldText(text = "Gastos de producción")
    repeat(quantityExpenses) {
        ProductExpenseTextLabel(viewModel, it)
    }
    NewExpenseLineButton { quantityExpenses++ }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun NewExpenseLineButton(incrementQuantityExpenses: () -> Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            enabled = true,
            onClick = { incrementQuantityExpenses() },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 17.dp, vertical = 7.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor,
                contentColor = Color.White,
                disabledBackgroundColor = disabledButtonColor,
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Añadir nuevo gasto")
        }
    }
}

@Composable
fun ProductExpenseTextLabel(
    viewModel: NewProductViewModel,
    id: Int
) {
    var quantity by rememberSaveable { mutableIntStateOf(1) }
    var quantityCurrency by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var costPrice by rememberSaveable { mutableDoubleStateOf(0.0) }

    var ml by rememberSaveable { mutableStateOf(false) }
    var gr by rememberSaveable { mutableStateOf(false) }
    var ud by rememberSaveable { mutableStateOf(true) }

    if (ml) quantityCurrency = "ml"
    else if (gr) quantityCurrency = "gr"
    else if (ud) quantityCurrency = "ud"

    val productExpense = SubProduct(id.toString(), name, costPrice, quantity, quantityCurrency)

    LazyRow(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            ExpenseQuantityTextField(quantity) {
                quantity = it
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseCurrencyMl(ml) {
                quantityCurrency = "ml"
                ml = true
                gr = false
                ud = false
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseCurrencyGr(gr) {
                quantityCurrency = "gr"
                ml = false
                gr = true
                ud = false
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseCurrencyUd(ud) {
                quantityCurrency = "ud"
                ml = false
                gr = false
                ud = true
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseNameTextField(name) {
                name = it
                viewModel.onExpensedProductChange(id, productExpense)
            }
            ExpenseCostPriceTextField(costPrice) {
                costPrice = it
                viewModel.onExpensedProductChange(id, productExpense)
            }
        }
    }
}

@Composable
fun ExpenseCostPriceTextField(costPrice: Double, onCostPriceChange: (Double) -> Unit) {
    OutlinedTextField(
        value = costPrice.toString(),
        onValueChange = {
            onCostPriceChange(it.toDouble())
        },
        placeholder = { Text(text = "Precio...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .width(120.dp)
            .height(52.dp),
        label = { Text(text = "Precio") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            backgroundColor = Color.White
        ),
    )
}

@Composable
fun ExpenseCurrencyUd(ud: Boolean, onClickButton: () -> Unit) {
    Button(
        enabled = true,
        onClick = { onClickButton() },
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 5.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (ud) buttonColor else Color.White,
            contentColor = if (ud) Color.White else buttonColor,
            disabledBackgroundColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "ud")
    }
}

@Composable
fun ExpenseCurrencyGr(gr: Boolean, onClickButton: () -> Unit) {
    Button(
        enabled = true,
        onClick = { onClickButton() },
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 5.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (gr) buttonColor else Color.White,
            contentColor = if (gr) Color.White else buttonColor,
            disabledBackgroundColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "gr")
    }
}

@Composable
fun ExpenseCurrencyMl(ml: Boolean, onClickButton: () -> Unit) {
    Button(
        enabled = true,
        onClick = { onClickButton() },
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 5.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (ml) buttonColor else Color.White,
            contentColor = if (ml) Color.White else buttonColor,
            disabledBackgroundColor = disabledButtonColor,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "ml")
    }

}

@Composable
fun ExpenseQuantityTextField(quantity: Int, onDescriptionChange: (Int) -> Unit) {
    OutlinedTextField(
        value = quantity.toString(),
        onValueChange = {
            onDescriptionChange(it.toInt())
        },
        placeholder = { Text(text = "Cantidad...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .width(70.dp)
            .height(52.dp),
        label = { Text(text = "Unds") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            backgroundColor = Color.White
        ),
    )
}

@Composable
fun ExpenseNameTextField(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = {
            onNameChange(it)
        },
        placeholder = { Text(text = "Nombre...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .width(180.dp)
            .height(52.dp),
        label = { Text(text = "Nombre") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            backgroundColor = Color.White
        ),
    )
}

@Composable
fun ProductPriceTextField(sellPrice: Double, onInsertChange: (Double) -> Unit) {
    OutlinedTextField(
        value = sellPrice.toString(),
        onValueChange = { onInsertChange(it.toDouble()) },
        placeholder = { Text(text = "Precio de venta...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .fillMaxWidth()
            .height(52.dp),
        label = { Text(text = "Precio €") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "price_icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            backgroundColor = Color.White
        ),
    )
}

@Composable
fun ProductDescriptionTextField(description: String, onInsertChange: (String) -> Unit) {
    OutlinedTextField(
        value = description,
        onValueChange = { onInsertChange(it) },
        placeholder = { Text(text = "Descripción del producto...", color = Color.Gray) },
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 7.dp)
            .fillMaxWidth()
            .height(52.dp),
        label = { Text(text = "Descripción") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "description_icon",
                tint = Color.Black
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            errorLabelColor = Color.Red,
            errorIndicatorColor = Color.Red,
            textColor = Color.Black,
            backgroundColor = Color.White
        ),
    )

}

@Composable
fun OnSaveProductButton(insertEnable: Boolean, imageUri: String?, viewModel: NewProductViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            enabled = insertEnable && imageUri != null,
            onClick = {
                viewModel.onSaveProduct(imageUri!!)
            },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 17.dp, vertical = 7.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor,
                contentColor = Color.White,
                disabledBackgroundColor = disabledButtonColor,
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Agregar Producto")
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun PreviewSelectedImage(imageUri: String?) {

}

/*
@Composable
fun ImageSelectorButton(galleryLauncher: ManagedActivityResultLauncher<String, Uri?>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { galleryLauncher.launch("image/*") },
            enabled = true,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 17.dp, vertical = 7.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor,
                contentColor = Color.White,
                disabledBackgroundColor = disabledButtonColor,
                disabledContentColor = Color.White
            )
        ) {
            Text(text = "Seleccionar Imagen")
        }
    }
}

 */