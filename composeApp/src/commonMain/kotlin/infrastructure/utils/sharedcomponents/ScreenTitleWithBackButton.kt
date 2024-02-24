package infrastructure.utils.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infrastructure.utils.values.buttonColor
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ScreenTitleWithBackButton(title: String, navController: Navigator) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackScreenButton(navController = navController, Color.White)
        Text(
            modifier = Modifier.padding(16.dp),
            text = title,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic
            ),
            color = buttonColor
        )
    }
}
