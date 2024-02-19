package application.auth.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import application.auth.ui.auth.components.HeaderImage
import application.auth.ui.login.components.ForgotPasswordButton
import application.auth.ui.login.components.LoginButton
import application.auth.ui.login.components.RememberRegisterLabel
import application.auth.ui.login.components.EmailTextField
import application.auth.ui.login.components.PasswordTextField
import domain.models.UiState
import infrastructure.utils.HorizontalLine
import infrastructure.utils.BasicAlertDialog
import infrastructure.utils.ShowLoadingView
import infrastructure.utils.authBackgroundColor
import infrastructure.utils.buttonColor
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: Navigator, initApp: () -> Unit) {
    val uiState: UiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.IDLE -> ShowLoginScreen(viewModel, navController)
        UiState.LOADING -> {
            ShowLoginScreen(viewModel, navController)
            ShowLoadingView()
        }
        UiState.ERROR -> {
            BasicAlertDialog(
                icon = Icons.Default.Warning,
                title = "Error de autenticación",
                body = "Las credenciales no són correctas",
                color = Color.Red,
                onConfirmation = { viewModel.finishLogin() },
            )
        }

        UiState.SUCCESS -> {
            viewModel.loadInitialData()
            initApp()
        }
    }
}

@Composable
private fun ShowLoginScreen(viewModel: LoginViewModel, navController: Navigator) {
    val email: String by viewModel.email.collectAsState()
    val password: String by viewModel.password.collectAsState()
    val loginEnable: Boolean by viewModel.loginEnable.collectAsState()

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
            EmailTextField(email) { viewModel.onLoginChange(it, password) }
            PasswordTextField(password) { viewModel.onLoginChange(email, it) }
            RememberRegisterLabel(navController)
        }
        item {
            LoginButton(loginEnable) { viewModel.onLoginSelected(email, password) }
        }
        item {
            HorizontalLine(buttonColor)
            ForgotPasswordButton()
        }
    }
}