package application.clients.domain.models

import domain.models.Purchase
import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val purchases: MutableList<Purchase>
)