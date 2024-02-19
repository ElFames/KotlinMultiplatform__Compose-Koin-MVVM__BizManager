package infrastructure.navigation


sealed class AppScreens(val route: String) {
    data object AuthScreen: AppScreens(route = "auth")
    data object RegisterScreen: AppScreens(route = "register")
    data object LoginScreen: AppScreens(route = "login")

    data object ClientDetailScreen: AppScreens(route = "detail_client")
    data object NewClientScreen: AppScreens(route = "new_client")
    data object EditClientScreen: AppScreens(route = "edit_client")

    data object PurchaseInvoicerScreen: AppScreens(route = "purchase_invoice")

    data object ProductDetailScreen: AppScreens(route = "detail_product")
    data object NewProductScreen: AppScreens(route = "new_product")
    data object EditProductScreen: AppScreens(route = "edit_product")
}