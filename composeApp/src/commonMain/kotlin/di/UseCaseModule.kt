package di

import domain.usecase.LoadInitialData
import org.koin.dsl.module

val useCaseModule = module {
    single { LoadInitialData() }
}