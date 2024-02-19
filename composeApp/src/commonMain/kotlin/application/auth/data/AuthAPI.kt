package application.auth.data

import application.auth.domain.models.MyUser
import application.auth.domain.models.UserForRegister
import domain.BASE_URL
import io.ktor.http.contentType
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.utils.io.core.use

class AuthAPI(private val client: HttpClient) {
    // Boolean is the body
    suspend fun changeEmail(newEmail: String, authHeader: String): HttpResponse {
        return client.use {
            it.put(BASE_URL) {
                url {
                    appendPathSegments("auth","changeEmail", newEmail)
                }
                headers {
                    append("Authorization", authHeader)
                }
                contentType(ContentType.Application.Json)
                parameter("email", newEmail)
            }
        }
    }

    // Boolean is the body
    suspend fun audience(authHeader: String): HttpResponse {
        return client.use {
            it.get(BASE_URL) {
                url {
                    appendPathSegments("auth","audience")
                }
                headers {
                    append("Authorization", authHeader)
                }
            }
        }

    }

    // HashMap<String,String> is the body
    suspend fun getNewToken(user: MyUser): HttpResponse {
        return client.use {
            it.post(BASE_URL) {
                url {
                    appendPathSegments("auth","login")
                }
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        }
    }

    // Boolean is the body
    suspend fun registerNewUser(user: UserForRegister): HttpResponse {
        return client.use {
            it.post(BASE_URL) {
                url {
                    appendPathSegments("auth","register")
                }
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        }
    }

    // Boolean is the body
    suspend fun changePassword(newPassword: String, authHeader: String): HttpResponse  {
        return client.use {
            it.put(BASE_URL) {
                url {
                    appendPathSegments("auth","changePassword", newPassword)
                }
                headers {
                    append("Authorization", authHeader)
                }
                contentType(ContentType.Application.Json)
                parameter("password", newPassword)
            }
        }

    }


}