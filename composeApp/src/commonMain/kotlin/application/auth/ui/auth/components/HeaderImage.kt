package application.auth.ui.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(res = "drawable/auth_header_image@2x.png"),
        contentDescription = "Imagen de encabezado de BizManager",
        contentScale = ContentScale.Crop,
        modifier = Modifier.width(Dp.Infinity).height(220.dp)
    )
}
