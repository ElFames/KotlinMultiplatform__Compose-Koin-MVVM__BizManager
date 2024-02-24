package application.products.domain.models

import application.products.domain.models.SubProduct
import kotlinx.serialization.Serializable

@Serializable
data class ProductForInsert(
    var productName: String,
    var description: String,
    var sellPrice: Double,
    val expensedProducts: MutableList<SubProduct>
)