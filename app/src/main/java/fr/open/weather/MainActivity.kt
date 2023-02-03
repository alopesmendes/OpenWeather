package fr.open.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.open.weather.presentation.navigation.NavigationGraph
import fr.open.weather.presentation.theme.WeatherTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationGraph(
                        navController = navController,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationGraphPreview() {
    WeatherTheme {
        val navController = rememberNavController()
        NavigationGraph(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
        )
    }
}