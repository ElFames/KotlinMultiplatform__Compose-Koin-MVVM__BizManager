package application.clients.ui.editclient

import application.clients.domain.ClientRepository
import application.clients.domain.models.Client
import domain.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import moe.tlaster.precompose.viewmodel.ViewModel

class EditClientViewModel(private val repository: ClientRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _client = MutableStateFlow<Client?>(null)
    val client: StateFlow<Client?> = _client

    fun getClient(clientId: String) {
        _uiState.value = UiState.LOADING
        val client = repository.getClient(clientId)
        _client.value = client
        _uiState.value = UiState.SUCCESS
    }

    fun updateClient(newClient: Client) {
        repository.updateClient(newClient)
    }
}