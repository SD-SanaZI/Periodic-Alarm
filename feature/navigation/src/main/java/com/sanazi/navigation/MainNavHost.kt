package com.sanazi.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoutes.AlarmsListRoutes
    ) {
        composable<NavRoutes.AlarmsListRoutes> {
            Text(it.destination.route.toString())
        }
        composable<NavRoutes.AddAlarmRoutes> {
            Text(it.destination.route.toString())
        }
    }
}
