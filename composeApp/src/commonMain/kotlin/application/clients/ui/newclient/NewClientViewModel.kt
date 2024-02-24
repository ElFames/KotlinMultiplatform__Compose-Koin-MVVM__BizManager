package application.clients.ui.newclient

import application.clients.domain.ClientRepository
import domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class NewClientViewModel(private val repository: ClientRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _name = MutableStateFlow<String>("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> = _email

    private val _phone = MutableStateFlow<String>("")
    val phone: StateFlow<String> = _phone

    private val _address = MutableStateFlow<String>("")
    val address: StateFlow<String> = _address

    private val _insertEnable = MutableStateFlow<Boolean>(false)
    val insertEnable: StateFlow<Boolean> = _insertEnable

    fun onInsertChange(name: String, email: String, phone: String, address: String) {
        _name.value = name
        _email.value = email
        _phone.value = phone
        _address.value = address
        _insertEnable.value =
            valuesNotEmpty() && isValidEmail(email) && isValidPhone(phone)
    }

    fun onSaveClient(name: String, email: String, phone: String, address: String) {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val response = repository.newClient(name, email, phone, address)
            _uiState.value =
                if (response.first) UiState.SUCCESS else UiState.ERROR
        }
    }

    private fun isValidPhone(phone: String): Boolean =
        phone.length == 9

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}")
        return emailRegex.matches(email)
    }

    private fun valuesNotEmpty(): Boolean {
        val values = mutableListOf(_name.value, _email.value, _phone.value.toString(), _address.value)
        return values.all { (it?:"").trim().isNotEmpty() }
    }
    fun finishInsert() {
        _uiState.value = UiState.IDLE
    }


}