package application.auth.data

import application.auth.domain.models.MyUser
import application.auth.domain.models.UserForRegister
import domain.Session
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode

class AuthService(
    private val authAPI: AuthAPI
) {
    private val authHeader = "Bearer ${Session.getCurrentToken()}"

    suspend fun login(email: String, password: String): Boolean {
        try {
            var myUser = Session.getCurrentUser()
            myUser =
                if (myUser != null) {
                    if (myUser.email == email && myUser.password == password) {
                        if (!checkToken()) {
                            val newToken = generateToken(email, password)
                            Session.updateToken(newToken)
                        }
                        MyUser(email, password)
                    } else {
                        val newToken = generateToken(email, password)
                        Session.updateToken(newToken)
                        MyUser(email, password)
                    }
                } else {
                    if (!checkToken()) {
                        val newToken = generateToken(email, password)
                        Session.updateToken(newToken)
                    }
                    MyUser(email, password)
                }
            Session.setCurrentUser(myUser)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    suspend fun register(username: String, email: String, password: String): Boolean {
        val response = authAPI.registerNewUser(
            UserForRegister(username, email, password)
        )
        return response.body() ?: false
    }

    suspend fun changePassword(newPassword: String): Boolean {
        val response = authAPI.changePassword(newPassword, authHeader)
        return response.body() ?: false
    }

    suspend fun changeEmail(newEmail: String): Boolean {
        val response = authAPI.changeEmail(newEmail, authHeader)
        return response.body() ?: false
    }


    private suspend fun generateToken(username: String, password: String): String {
        val response = authAPI.getNewToken(
            MyUser(username, password)
        )
        if (response.status == HttpStatusCode.OK) {
            val token = response.call.body<HashMap<String, String>>()["token"]
            return token ?: throw Exception("Token no encontrado")
        } else {
            throw Exception("Error al iniciar sesión: ${response.status}")
        }
    }

    private suspend fun checkToken(): Boolean {
        return if (Session.getCurrentToken() == null) {
            false
        } else {
            val response = authAPI.audience(authHeader)

            when (response.status) {
                HttpStatusCode.Unauthorized -> false
                else -> true
            }
        }
    }
}