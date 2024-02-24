package application.clients.data

import domain.Session
import application.clients.domain.models.Client
import application.clients.domain.models.ClientToInsert
import io.ktor.client.call.body

class ClientsService(private val clientsAPI: ClientsAPI
) {
    private val authHeader = "Bearer ${Session.getCurrentToken()}"

    suspend fun loadClients(): MutableList<Client> {
        return clientsAPI.loadClients("clients/loadAll", authHeader).body() ?: mutableListOf()
    }

    suspend fun insertClient(name: String, email: String, phone: String, address: String): String {
        val clientToInsert = ClientToInsert(name, email, phone, address)
        return clientsAPI.insertClient(clientToInsert, authHeader).body() ?: "No hay conexión con el servidor"
    }

    fun updateClient(newClient: Client): Boolean {
        return true
    }

}