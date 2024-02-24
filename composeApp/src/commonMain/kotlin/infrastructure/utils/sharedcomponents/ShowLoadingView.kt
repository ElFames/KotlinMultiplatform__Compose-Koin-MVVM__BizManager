package infrastructure.utils.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import infrastructure.utils.values.authBackgroundColor
import kotlinx.coroutines.delay

@Composable
fun ShowLoadingView() {
    val isJumping by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(authBackgroundColor.copy(alpha = 0.6f))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clipToBounds()
        ) {
            repeat(3) {
                JumpingDot(isJumping = isJumping, delay = it * 200)
            }
        }
    }
}

@Composable
fun JumpingDot(isJumping: Boolean, delay: Int) {
    var translateY by remember { mutableStateOf(0.dp) }

    LaunchedEffect(isJumping) {
        if (isJumping) {
            while (true) {
                translateY = (-4).dp
                delay(300)
                translateY = 0.dp
                delay(300)
            }
        }
    }

    Icon(
        imageVector = Icons.Default.CheckCircle,
        contentDescription = null,
        modifier = Modifier
            .size(24.dp)
            .offset(y = translateY)
            .background(Color.Black)
            .clip(CircleShape)
    )
}