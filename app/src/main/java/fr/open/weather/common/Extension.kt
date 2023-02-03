package fr.open.weather.common

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import java.math.BigDecimal
import java.math.RoundingMode

object Extension {
    fun NavHostController.navigateTo(destination: String) {
        navigate(destination) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    fun Double.format(digits: Int): Double {
        val decimal = BigDecimal(this).setScale(digits, RoundingMode.HALF_EVEN)
        return decimal.toDouble()
    }
}