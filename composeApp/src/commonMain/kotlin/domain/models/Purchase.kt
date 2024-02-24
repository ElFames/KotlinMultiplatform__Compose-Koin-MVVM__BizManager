package domain.models

import application.products.Product
import kotlinx.serialization.Serializable


@Serializable
data class Purchase(
    val id: String,
    val products: MutableList<Product>,
    val dateTime: DateTime,
    val totalPrice: Double,
    val clientId: String?
)