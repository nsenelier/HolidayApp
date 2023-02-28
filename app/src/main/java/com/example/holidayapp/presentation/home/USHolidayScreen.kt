package com.example.holidayapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.holidayapp.core.components.TopAppBarContent
import com.example.holidayapp.domain.model.HolidayDetail

@Composable
fun USHolidayScreen(
    navController: NavController,
    viewModel: USHolidayViewModel = hiltViewModel()

){
    val state by viewModel.uiState.collectAsState()
    val holidays = state.holidays

    // Launch a coroutine bound to the scope of the composable, viewModel relaunched
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onEvent(USHolidayEvent.LoadHoliday)
    })

    if(holidays.isNotEmpty()){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
           TopAppBarContent()
            LazyColumn(
                modifier = Modifier.padding(10.dp)
            ){
                items(state.holidays.size){i ->
                    val holiday = state.holidays[i]

                    FavoriteButton(holiday = holiday, viewModel = viewModel, modifier = Modifier)
                    ShowItem(
                        holiday = holiday,
                        modifier = Modifier
                            .fillMaxWidth()
//                            .clickable {
//                                navController.navigate(route = "$HOLIDAY_DETAIL_SCREEN/${holiday.name}")
//                            }
                    )
                    IconButton(onClick = {
                        viewModel.onEvent(
                            USHolidayEvent.OnFavoriteSelected(
                                holiday
                            )
                        )
                    }
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Add),
                            contentDescription = "Add Favorite",
                            tint = Color.White
                        )
                    }
                    if (i < state.holidays.size) {
                        Divider(
                            modifier = Modifier.padding(
                                vertical = 16.dp
                            )
                        )
                    }
                }

            }
        }
    }
    else{
       if(state.error.isNotBlank()) {
            Toast.makeText(LocalContext.current, "Error has occurred..", Toast.LENGTH_LONG).show()
       }
        if(state.isLoading){
            Toast.makeText(LocalContext.current, "Loading in progress..", Toast.LENGTH_LONG).show()
        }
    }

}

@Composable
fun ShowItem(
    holiday: HolidayDetail,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            Text(
                text = holiday.countryCode,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = holiday.localName,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = holiday.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = holiday.date,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 20.dp)
            )

        }
    }

}

@Composable
fun FavoriteButton(
    modifier: Modifier,
    viewModel: USHolidayViewModel = hiltViewModel(),
    holiday: HolidayDetail
) {
    var isFavorite by rememberSaveable(holiday) { mutableStateOf(holiday.isFavorite) }

    IconButton(onClick = {
        isFavorite = !isFavorite
        holiday.isFavorite = isFavorite
        if (isFavorite) {
            viewModel.onEvent(USHolidayEvent.OnFavoriteSelected(holiday))
        } else {
            viewModel.onEvent(USHolidayEvent.DeleteFavorite(holiday.id))
        }
    }) {
        val tintColor = if (isFavorite) Color.Red else Color.White
        Icon(
            painter = rememberVectorPainter(Icons.Default.Favorite),
            contentDescription = "Favorite Icon",
            tint = tintColor,
            modifier = modifier.padding(start = 190.dp, bottom = 30.dp)
        )
    }
}
