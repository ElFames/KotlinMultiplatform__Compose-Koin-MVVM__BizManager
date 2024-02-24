package application.products.ui.myproducts

import application.products.domain.ProductsRepository
import domain.models.UiState
import application.products.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class MyProductsViewModel(
    private val repository: ProductsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _products = MutableStateFlow(repository.getProducts())
    val products: StateFlow<MutableList<Product>> = _products

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            _products.value = repository.loadProducts()
            _uiState.value = UiState.SUCCESS
        }
    }

}