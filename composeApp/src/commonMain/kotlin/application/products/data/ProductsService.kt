package application.products.data

import domain.Session
import application.products.domain.models.ProductForInsert

class ProductsService(private val productsService: ProductsAPI) {
    private val myToken get() = Session.getCurrentToken()

    fun newProduct(newProduct: ProductForInsert, imageUri: String): Int {
        //val response = sessionService.newProduct(newProduct, imageUri)
        return -1
    }


}