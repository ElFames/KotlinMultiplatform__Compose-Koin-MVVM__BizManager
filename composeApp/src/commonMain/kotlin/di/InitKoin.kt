package di

import application.auth.di.authModule
import org.koin.core.context.startKoin

fun InitKoin() {
    startKoin {
        modules(networkModule, useCaseModule, authModule)
    }
}