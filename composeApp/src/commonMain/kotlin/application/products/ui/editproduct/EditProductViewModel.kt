package application.products.ui.editproduct

import application.products.domain.ProductsRepository
import domain.models.UiState
import application.products.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import moe.tlaster.precompose.viewmodel.ViewModel

class EditProductViewModel(
    private val repository: ProductsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    fun getProduct(productId: String) {
        _uiState.value = UiState.LOADING
        val product = repository.getProduct(productId)
        _product.value = product
        _uiState.value = UiState.SUCCESS
    }

    fun updateProduct(newProduct: Product) {
        repository.updateProduct(newProduct)
    }
}