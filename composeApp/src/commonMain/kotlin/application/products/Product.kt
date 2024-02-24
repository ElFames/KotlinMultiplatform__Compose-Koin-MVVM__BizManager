package application.products

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    var name: String,
    var description: String,
    var unds: Int = 0,
    var sellPrice: Double,
    val expenses: MutableList<SubProduct>,
    val imageUrl: String? = null
)

@Serializable
data class SubProduct(
    val id: String,
    var name: String,
    var costPrice: Double,
    var quantity: Int,
    var quantityCurrency: String
)