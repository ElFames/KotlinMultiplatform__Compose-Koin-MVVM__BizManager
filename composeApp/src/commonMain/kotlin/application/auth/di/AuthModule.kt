package application.auth.di

import application.auth.data.AuthAPI
import application.auth.data.AuthService
import application.auth.domain.AuthRepository
import application.auth.ui.login.LoginViewModel
import application.auth.ui.register.RegisterViewModel
import org.koin.dsl.module

val authModule = module {
    single { AuthAPI(get()) }
    single { AuthService(get()) }
    single { AuthRepository(get()) }
    factory { LoginViewModel(get(), get()) }
    factory { RegisterViewModel(get()) }
}