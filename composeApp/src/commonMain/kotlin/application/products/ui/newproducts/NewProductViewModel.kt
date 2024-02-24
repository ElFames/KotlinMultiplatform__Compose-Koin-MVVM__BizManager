package application.products.ui.newproducts

import application.products.domain.ProductsRepository
import domain.models.UiState
import application.products.domain.models.ProductForInsert
import application.products.domain.models.SubProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class NewProductViewModel(
    private val repository: ProductsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState

    private val _productName = MutableStateFlow<String>("")
    val productName: StateFlow<String> = _productName

    private val _description = MutableStateFlow<String>("")
    val description: StateFlow<String> = _description

    private val _sellPrice = MutableStateFlow<Double>(0.0)
    val sellPrice: StateFlow<Double> = _sellPrice

    private val _expensedProducts = MutableStateFlow<MutableMap<Int, SubProduct>>(mutableMapOf())
    val expensedProducts: StateFlow<MutableMap<Int, SubProduct>> = _expensedProducts

    private val _insertEnable = MutableStateFlow<Boolean>(false)
    val insertEnable: StateFlow<Boolean> = _insertEnable

    fun onInsertChange(name: String, description: String, sellPrice: Double) {
        _productName.value = name
        _description.value = description
        _sellPrice.value = sellPrice
        _insertEnable.value =
            valuesNotEmpty()
    }

    fun onExpensedProductChange(position: Int, subProduct: SubProduct) {
        _expensedProducts.value?.set(position, subProduct)
    }

    fun onSaveProduct(imageUri: String) {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
            val newProduct = ProductForInsert(
                _productName.value,
                _description.value,
                _sellPrice.value,
                _expensedProducts.value.map { it.value }.toMutableList()
            )
            val response = repository.newProduct(newProduct, imageUri)
            _uiState.value =
                if (response) UiState.SUCCESS else UiState.ERROR
        }
    }

    private fun valuesNotEmpty(): Boolean {
        val values = mutableListOf(_productName.value, _description.value, _sellPrice.value.toString())
        return values.all { (it).trim().isNotEmpty() }
    }
    fun finishInsert() {
        _uiState.value = UiState.IDLE
    }
}