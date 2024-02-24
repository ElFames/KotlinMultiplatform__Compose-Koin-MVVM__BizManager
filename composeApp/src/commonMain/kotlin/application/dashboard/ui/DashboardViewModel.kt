package application.dashboard.ui

import application.dashboard.domain.DashboardRepository
import application.products.Product
import domain.models.UiState
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class DashboardViewModel(private val repository: DashboardRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _bestSellers = MutableStateFlow<MutableList<Product>?>(null)
    val bestSellers: StateFlow<MutableList<Product>?> = _bestSellers

    private val _moreProfit = MutableStateFlow<MutableList<Product>?>(null)
    val moreProfit: StateFlow<MutableList<Product>?> = _moreProfit

    private val _income = MutableStateFlow<String>("")
    val income: StateFlow<String> = _income

    private val _expenses = MutableStateFlow<String>("")
    val expenses: StateFlow<String> = _expenses

    private val _profit = MutableStateFlow<String>("")
    val profit: StateFlow<String> = _profit

    private val _numOfSales = MutableStateFlow<String>("")
    val numOfSales: StateFlow<String> = _numOfSales

    private val _filter = MutableStateFlow(Filter.DIA)
    val filter: StateFlow<Filter> = _filter

    fun updateFilterStatistics(currentFilter: Filter) {
        this._filter.value = currentFilter

        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            var response = getStatistics()
            response = getBestSellers()
            response = getMoreProfit()
            response = true // simulate a successful response
            _uiState.value =
                if (response) UiState.SUCCESS
                else UiState.ERROR
        }
    }

    private suspend fun getMoreProfit(): Boolean {
        val newMoreProfit = repository.getMoreProfit(_filter.value)
        _moreProfit.value = newMoreProfit
        return newMoreProfit.isNotEmpty()
    }

    private suspend fun getBestSellers(): Boolean {
        val newBestSellers = repository.getBestSellers(_filter.value)
        _bestSellers.value = newBestSellers
        return newBestSellers.isNotEmpty()
    }

    private suspend fun getStatistics(): Boolean {
        val statistics = repository.getStatistics(_filter.value)
        _numOfSales.value = statistics.numOfSales.toString()
        _income.value = statistics.income.toString()
        _expenses.value = statistics.expenses.toString()
        _profit.value = statistics.profit.toString()
        return statistics.numOfSales != -1
    }

    fun hideError() {
        _uiState.value = UiState.SUCCESS
    }

}