package fr.open.weather.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.open.weather.common.Extension.navigateTo
import fr.open.weather.presentation.screen.WeatherScreen
import fr.open.weather.presentation.screen.WelcomeScreen
import fr.open.weather.presentation.view_models.WeatherViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Screen = Screen.Welcome,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onNavigate = { navController.navigateTo(it) },
            )
        }

        composable(route = Screen.Weather.route) {
            val weatherViewModel: WeatherViewModel = hiltViewModel()

            WeatherScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, 8.dp),
                onBack = navController::popBackStack,
                progressVisibility = weatherViewModel.progressVisibility,
                onProgressVisibilityChange = { weatherViewModel.progressVisibility = it },
                launchWeatherApiCall = weatherViewModel::launchWeatherApiCall,
                weatherDataBaseStates = weatherViewModel.weatherDataBaseStates,
                onClear = { weatherViewModel.weatherDataBaseStates.clear() }
            )
        }
    }
}