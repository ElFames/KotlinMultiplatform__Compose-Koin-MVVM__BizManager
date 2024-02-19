import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.InitKoin
import di.networkModule
import infrastructure.navigation.AppNavigation
import moe.tlaster.precompose.PreComposeApp
import org.koin.core.context.startKoin

@Composable
fun BizManagerApp() {
    MaterialTheme {
        PreComposeApp {
            InitKoin()
            AppNavigation()
        }
    }
}
