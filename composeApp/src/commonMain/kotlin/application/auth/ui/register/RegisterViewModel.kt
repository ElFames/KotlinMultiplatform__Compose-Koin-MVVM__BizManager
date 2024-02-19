package application.auth.ui.register

import application.auth.domain.AuthRepository
import domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class RegisterViewModel(private val repository: AuthRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState = _uiState.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _registerEnable = MutableStateFlow(false)
    val registerEnable = _registerEnable.asStateFlow()


    fun onRegisterChange(name: String, email: String, password: String, confirmPassword: String) {
        _name.value = name
        _email.value = email
        _password.value = password
        _confirmPassword.value = confirmPassword
        _registerEnable.value = isValidEmail(email) && isValidPassword(password) && password == confirmPassword && name.isNotEmpty()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun isValidEmail(email: String): Boolean =
        Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]").matches(email)

    fun onRegisterSelected(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val response = repository.register(name, email, password)
            if (response) _uiState.value = UiState.SUCCESS
            else _uiState.value = UiState.ERROR
        }
    }

    fun finishRegister() {
        _uiState.value = UiState.IDLE
    }
}