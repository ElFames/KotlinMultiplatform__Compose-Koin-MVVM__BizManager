package application.products.di

import application.products.data.ProductsAPI
import application.products.data.ProductsService
import application.products.domain.ProductsRepository
import application.products.ui.editproduct.EditProductViewModel
import application.products.ui.myproducts.MyProductsViewModel
import application.products.ui.newproducts.NewProductViewModel
import application.products.ui.productdetail.ProductDetailViewModel
import org.koin.dsl.module

val productsModule = module {
     single { ProductsAPI(get()) }
     single { ProductsService(get()) }
     single { ProductsRepository(get()) }
     factory { EditProductViewModel(get()) }
     factory { NewProductViewModel(get()) }
     factory { ProductDetailViewModel(get()) }
     factory { MyProductsViewModel(get()) }
}