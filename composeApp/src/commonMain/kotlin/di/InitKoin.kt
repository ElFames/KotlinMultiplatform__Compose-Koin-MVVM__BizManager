package di

import application.auth.di.authModule
import application.clients.di.clientsModule
import application.dashboard.di.dashboardModule
import org.koin.core.context.startKoin

fun InitKoin() {
    startKoin {
        modules(
            networkModule,
            useCaseModule,
            authModule,
            clientsModule,
            dashboardModule
        )
    }
}