package application.clients.di

import application.clients.data.ClientsAPI
import application.clients.data.ClientsService
import application.clients.domain.ClientRepository
import application.clients.ui.clientdetail.ClientDetailViewModel
import application.clients.ui.editclient.EditClientScreen
import application.clients.ui.editclient.EditClientViewModel
import application.clients.ui.myclients.MyClientsViewModel
import application.clients.ui.newclient.NewClientViewModel
import org.koin.dsl.module

val clientsModule = module {
    single { ClientsAPI(get()) }
    single { ClientsService(get()) }
    single { ClientRepository(get()) }
    factory { MyClientsViewModel(get()) }
    factory { ClientDetailViewModel(get()) }
    factory { NewClientViewModel(get()) }
    factory { EditClientViewModel(get()) }
}