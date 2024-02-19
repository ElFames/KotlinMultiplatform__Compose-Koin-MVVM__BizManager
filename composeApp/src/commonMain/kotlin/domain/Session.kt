package domain

import application.auth.domain.models.MyUser

object Session {
    // TODO: guardar estas dos variables en localstorage cifradas
    private var currentToken: String? = null
    private var currentUser: MyUser? = null

    fun getCurrentToken() = currentToken
    fun updateToken(token: String) {
        currentToken = token
    }
    fun getCurrentUser() = currentUser
    fun setCurrentUser(user: MyUser) {
        currentUser = user
    }

}