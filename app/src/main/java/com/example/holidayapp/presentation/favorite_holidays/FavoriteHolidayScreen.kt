package com.example.holidayapp.presentation.favorite_holidays

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.holidayapp.core.components.TopAppBarContent
import com.example.holidayapp.domain.model.HolidayDetail


@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteHolidayViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val favoriteHoliday = uiState.favoriteHoliday

    // Launch a coroutine bound to the scope of the composable
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onEvent(FavoriteHolidayEvent.LoadFavoriteHoliday)
    })

    if(favoriteHoliday.isNotEmpty()){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBarContent()
            LazyColumn(
                modifier = Modifier.padding(10.dp)
            ) {
                items(uiState.favoriteHoliday.size){ i ->
                    val holiday = uiState.favoriteHoliday[i]

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ){
                        CardContent(
                            holiday = holiday,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        IconButton(onClick = {
                            viewModel.onEvent(
                                FavoriteHolidayEvent.OnDeleteSelected(
                                    holiday.id
                                )
                            )
                        }
                        ) {
                            Icon(
                                painter = rememberVectorPainter(Icons.Default.Delete),
                                contentDescription = "Delete Favorite",
                                tint = Color.White
                            )
                        }
                    }
                    if (i < uiState.favoriteHoliday.size) {
                        Divider(
                            modifier = Modifier.padding(
                                vertical = 16.dp
                            )
                        )
                    }
                }
            }
        }
        }else if(uiState.isLoading){
            Toast.makeText(LocalContext.current, "Loading in progress..", Toast.LENGTH_LONG).show()
        }else if (!uiState.error.contains("")){
            Toast.makeText(LocalContext.current, "Error has occurred..", Toast.LENGTH_LONG).show()
        }else{
            Text(
                text = "No Data",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start=40.dp,top = 30.dp,)
            )
        }

}

@Composable
private fun CardContent(holiday: HolidayDetail,
modifier: Modifier= Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = holiday.countryCode)
            if (expanded) {
                Text(
                    text = holiday.name
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    "Show_less"
                } else {
                    "Show_more"
                }
            )
        }
    }
}
