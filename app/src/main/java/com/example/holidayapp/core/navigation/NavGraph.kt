package com.example.holidayapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.holidayapp.presentation.favorite_holidays.FavoriteHolidayViewModel
import com.example.holidayapp.presentation.favorite_holidays.FavoriteScreen
import com.example.holidayapp.presentation.home.USHolidayScreen
import com.example.holidayapp.presentation.home.USHolidayViewModel
import com.example.holidayapp.presentation.settings.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController,
             favoriteHolidayViewModel: FavoriteHolidayViewModel = hiltViewModel(),
             usHolidayViewModel: USHolidayViewModel = hiltViewModel()
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            USHolidayScreen(navController = navController, viewModel = usHolidayViewModel)
        }
        composable(BottomNavItem.Favorite.screen_route) {
            FavoriteScreen(navController=navController, viewModel = favoriteHolidayViewModel)
        }
        composable(BottomNavItem.Setting.screen_route) {
            SettingsScreen()
        }


    }
}