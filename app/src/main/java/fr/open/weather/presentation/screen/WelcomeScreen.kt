package fr.open.weather.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.open.weather.R
import fr.open.weather.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(text = stringResource(id = R.string.title_welcome))
                }
            )
        }
    ) {
        Column(
            modifier = modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )

            ElevatedButton(
                onClick = { onNavigate(Screen.Weather.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.view_weather))
            }
        }
    }
    
}
