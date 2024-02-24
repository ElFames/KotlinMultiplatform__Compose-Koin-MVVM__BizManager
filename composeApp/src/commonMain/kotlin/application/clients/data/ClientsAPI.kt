package application.clients.data

import domain.BASE_URL
import application.clients.domain.models.ClientToInsert
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ClientsAPI(private val httpClient: HttpClient) {
    suspend fun loadClients(url: String, authHeader: String): HttpResponse {
        return httpClient.get {
            url("$BASE_URL/$url")
            header("Authorization", authHeader)
        }
    }

    suspend fun insertClient(clientToInsert: ClientToInsert, authHeader: String): HttpResponse {
        return httpClient.put {
            url("$BASE_URL/client/insert/${clientToInsert}")
            header("Authorization", authHeader)
            contentType(ContentType.Application.Json)
            setBody(clientToInsert)
        }
    }
}