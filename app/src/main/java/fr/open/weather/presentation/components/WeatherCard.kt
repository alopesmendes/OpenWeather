package fr.open.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import fr.open.weather.R

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    icon: String,
    cityName: String,
    minTemp: String,
    temp: String,
    maxTemp: String,
    description: String,
) {
    ElevatedCard(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.Start,
        ) {

            // PROBLEM cannot see icon
            Image(
                painter = rememberAsyncImagePainter("http://openweathermap.org/img/wn/$icon@2x.png"),
                contentDescription = "icon weather",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = cityName,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = stringResource(id = R.string.min_temp, minTemp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline,
                )
                Text(
                    text = stringResource(id = R.string.temp, temp),
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = stringResource(id = R.string.max_temp, maxTemp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardPreview() {
    WeatherCard(
        icon = "10d",
        cityName = "paris",
        modifier = Modifier.fillMaxSize(),
        temp = "50",
        maxTemp = "52",
        minTemp = "40",
        description = ""
    )
}