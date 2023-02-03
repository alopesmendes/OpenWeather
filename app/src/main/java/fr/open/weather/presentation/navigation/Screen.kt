package fr.open.weather.presentation.navigation

sealed class Screen(
    val route: String,
) {
    object Welcome : Screen(
        route = "Welcome",
    )

    object Weather : Screen(
        route = "Weather",
    )
}
