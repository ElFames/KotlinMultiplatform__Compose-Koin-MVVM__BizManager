package application.auth.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import application.auth.ui.auth.components.HeaderImage
import application.auth.ui.login.components.EmailTextField
import application.auth.ui.login.components.PasswordTextField
import application.auth.ui.register.components.BottomInfoText
import application.auth.ui.register.components.ConfirmPasswordTextField
import application.auth.ui.register.components.RegisterButton
import application.auth.ui.register.components.RememberLoginLabel
import domain.models.UiState
import application.auth.ui.register.components.UsernameTextField
import infrastructure.navigation.AppScreens
import infrastructure.utils.dialogs.BasicAlertDialog
import infrastructure.utils.sharedcomponents.HorizontalLine
import infrastructure.utils.sharedcomponents.ShowLoadingView
import infrastructure.utils.values.authBackgroundColor
import infrastructure.utils.values.buttonColor
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navigator: Navigator) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState) {
        UiState.LOADING -> {
            ShowLoadingView()
        }
        UiState.ERROR -> {
            BasicAlertDialog(
                icon = Icons.Default.Warning,
                title = "Error de registro",
                body = "Ocurrió un error al intentar registrarte, por favor intenta de nuevo",
                color = Color.Red,
                onConfirmation = { viewModel.finishRegister() }
            )
        }
        UiState.SUCCESS -> {
            BasicAlertDialog(
                icon = Icons.Default.Done,
                title = "Éxito en el registro",
                body = "Tu registro se ha realizado con éxito, por favor inicia sesión para continuar",
                color = Color.Red,
                onConfirmation = { navigator.navigate(AppScreens.LoginScreen.route) }
            )
        }
        UiState.IDLE -> {
            RegisterScreenContent(viewModel, navigator)
        }
    }

}

@Composable
fun RegisterScreenContent(viewModel: RegisterViewModel, navigator: Navigator) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val confirmPassword by viewModel.confirmPassword.collectAsStateWithLifecycle()
    val registerEnable by viewModel.registerEnable.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .background(color = authBackgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            HeaderImage()
        }
        item {
            UsernameTextField(name) {
                viewModel.onRegisterChange(name = it, email, password, confirmPassword)
            }
            EmailTextField(email) {
                viewModel.onRegisterChange(name, email = it, password, confirmPassword)
            }
            PasswordTextField(password) {
                viewModel.onRegisterChange(name, email, password = it, confirmPassword)
            }
            ConfirmPasswordTextField(confirmPassword) {
                viewModel.onRegisterChange(name, email, password, confirmPassword = it)
            }
            RememberLoginLabel(navigator)
        }
        item {
            RegisterButton(registerEnable) { viewModel.onRegisterSelected(name, email, password) }
        }
        item {
            HorizontalLine(buttonColor)
            BottomInfoText()
        }
    }
}