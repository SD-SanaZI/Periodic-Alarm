package com.sanazi.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoutes {

    @Serializable
    data object AlarmsListRoutes : NavRoutes

    @Serializable
    data object AddAlarmRoutes : NavRoutes
}