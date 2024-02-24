package domain.usecase

import application.clients.domain.ClientRepository
import application.dashboard.domain.DashboardRepository
import application.products.domain.ProductsRepository

class LoadInitialData(
    private val clientsRepository: ClientRepository,
    private val dashboardRepository: DashboardRepository,
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke() {
        clientsRepository.loadClients()
        dashboardRepository.loadStatistics()
        productsRepository.loadProducts()

    }
}