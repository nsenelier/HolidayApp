package com.example.holidayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.holidayapp.core.navigation.BottomNav
import com.example.holidayapp.core.navigation.NavGraph
import com.example.holidayapp.presentation.favorite_holidays.FavoriteHolidayViewModel
import com.example.holidayapp.presentation.home.USHolidayViewModel
import com.example.holidayapp.ui.theme.HolidayAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HolidayAppTheme {
                HolidayApp()
            }
        }
    }
}



@Composable
fun HolidayApp(){
    val navController = rememberNavController()
    val favoriteHolidayViewModel: FavoriteHolidayViewModel = hiltViewModel()
    val usHolidayViewModel: USHolidayViewModel = hiltViewModel()

    Scaffold(
        bottomBar = { BottomNav(navController = navController) },
        content = { padding -> Column(modifier = Modifier.padding(padding)){
            NavGraph(navController = navController,
                favoriteHolidayViewModel=favoriteHolidayViewModel,
                usHolidayViewModel=usHolidayViewModel
            )
        } },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    )
}