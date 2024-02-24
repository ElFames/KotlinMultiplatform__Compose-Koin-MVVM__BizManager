package application.clients.ui.myclients

import application.clients.domain.ClientRepository
import application.clients.domain.models.Client
import domain.models.UiState
import infrastructure.utils.values.Formats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class MyClientsViewModel(private val repository: ClientRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _clientToSearch = MutableStateFlow("")
    val clientToSearch: StateFlow<String> = _clientToSearch

    private val _clients = MutableStateFlow(repository.getClients())
    val clients: StateFlow<MutableList<Client>> = _clients

    private val _clientRanking = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val clientRanking: StateFlow<List<Pair<String, String>>> = _clientRanking

    fun onClientToSearchChange(newClientToSearch: String) {
        _clientToSearch.value = newClientToSearch
        _clients.value = repository.searchClient(newClientToSearch)
    }

    fun getClientRanking() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val clientRanking = mutableListOf<Pair<String, String>>()
            val ranking = repository.getClientRanking()
            ranking.forEach {
                clientRanking.add(Pair(it.first, Formats.price(it.second)))
            }
            _clientRanking.value = clientRanking
            _uiState.value = UiState.SUCCESS
        }
    }

    fun loadClients() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            _clients.value = repository.loadClients()
            _uiState.value = UiState.SUCCESS
        }
    }

}