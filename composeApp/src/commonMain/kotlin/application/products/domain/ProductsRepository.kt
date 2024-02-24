package application.products.domain

import application.products.domain.models.Product
import application.products.data.ProductsService
import application.products.domain.models.ProductForInsert
import application.products.domain.models.SubProduct
import java.util.UUID

class ProductsRepository(
    private val productsService: ProductsService
) {
    private var products = mutableListOf<Product>()
    fun getProducts() = products
    fun loadProducts(): MutableList<Product> {
        return products.ifEmpty {
            // products = productsService.getProducts()
            products = productsTest
            products
        }
    }

    fun getProduct(productId: String): Product {
        val product = products.find { it.id == productId }
        return product!!
    }

    fun updateProduct(newProduct: Product) {

    }

    fun newProduct(newProduct: ProductForInsert, imageUri: String): Boolean {
        val response = productsService.newProduct(newProduct, imageUri)
        if (response == -1) {
            return false
        } else {
            products.add(
                Product(
                    id = response.toString(),
                    name = newProduct.productName,
                    description = newProduct.description,
                    sellPrice = newProduct.sellPrice,
                    unds = 0,
                    expenses = newProduct.expensedProducts,
                    imageUrl = "larutadelaimagendemiapi.com/image/$response.png"
                )
            )
            return true
        }
    }

}

val productsTest = MutableList(10) { productId ->
    Product(
        id = UUID.randomUUID().toString(),
        name = "Producto${productId}os",
        description = "Descripción del producto $productId",
        sellPrice = 20.0 + productId * 5,
        unds = 0,
        expenses = MutableList(3) { expenseId ->
            SubProduct(
                id = UUID.randomUUID().toString(),
                name = "SubProducto $expenseId",
                costPrice = 8.0,
                quantity = expenseId + 2,
                quantityCurrency = if (productId % 2 == 0) "g" else "ml"
            )
        },
        imageUrl = if (productId % 2 == 0) "https://onlineblink.com/cdn/shop/products/lifting_de_cejas_web.jpg?v=1564659442" else null
    )
}