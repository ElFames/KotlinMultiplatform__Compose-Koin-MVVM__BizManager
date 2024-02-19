package application.auth.domain

import application.auth.data.AuthService


class AuthRepository(
    private val authService: AuthService
) {
    suspend fun login(email: String, password: String): Boolean {
        return authService.login(email, password)
    }

    suspend fun register(name: String, email: String, password: String): Boolean {
        return authService.register(name, email, password)
    }
}