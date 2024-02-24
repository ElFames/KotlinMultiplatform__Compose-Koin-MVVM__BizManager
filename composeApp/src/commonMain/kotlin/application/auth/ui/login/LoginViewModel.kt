package application.auth.ui.login

import domain.models.UiState
import application.auth.domain.AuthRepository
import domain.usecase.LoadInitialData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class LoginViewModel(
    private val repository: AuthRepository,
    private val loadInitData: LoadInitialData
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.SUCCESS)
    val uiState: StateFlow<UiState> = _uiState

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password

    private val _loginEnable = MutableStateFlow<Boolean>(false)
    val loginEnable: StateFlow<Boolean> = _loginEnable

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}")
        return emailRegex.matches(email)
    }

    fun onLoginSelected(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING

            delay(3000) // simulate network delay

            var response = repository.login(email, password)
            response = true // simulate login success

            _uiState.value =
                if (response) UiState.SUCCESS else UiState.ERROR
        }
    }

    fun loadInitialData() {
        viewModelScope.launch {
            loadInitData()
        }
    }
    fun finishLogin() {
        _uiState.value = UiState.IDLE
    }

}
