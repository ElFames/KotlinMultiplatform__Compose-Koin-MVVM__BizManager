package application.dashboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fames.systems.bizmanager.application.dashboard.domain.models.Filter
import application.dashboard.ui.DashboardViewModel

@Composable
fun FilterDashboardBar(viewModel: DashboardViewModel) {
    val selectedFilter: Filter by viewModel.filter.collectAsState(initial = Filter.DIA)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Text(textAlign = TextAlign.Center,
                modifier = Modifier.width(75.dp),
                text = selectedFilter.filterName,
                color = Color.LightGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(10.dp))
            FilterButton {
                val newFilter = when (selectedFilter) {
                    Filter.DIA -> Filter.SEMANA
                    Filter.SEMANA -> Filter.MES
                    else -> Filter.DIA
                }
                viewModel.updateFilterStatistics(newFilter)
            }
        }
    }
}

@Composable
fun FilterButton(onClickFilterButton: () -> Unit) {
    Card(backgroundColor = Color.Gray, shape = CircleShape) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .size(26.dp)
                .clickable { onClickFilterButton() },
            imageVector = Icons.Default.Refresh,
            contentDescription = "info icon"
        )
    }
}