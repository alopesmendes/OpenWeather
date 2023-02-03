package fr.open.weather.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.open.weather.R
import fr.open.weather.common.Extension.format
import fr.open.weather.domain.model.BaseState
import fr.open.weather.domain.model.ProgressVisibility
import fr.open.weather.domain.model.WeatherData
import fr.open.weather.presentation.components.ProgressBarWithText
import fr.open.weather.presentation.components.WeatherCard
import fr.open.weather.presentation.components.state.rememberProgressValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    progressVisibility: ProgressVisibility,
    onProgressVisibilityChange: (ProgressVisibility) -> Unit,
    launchWeatherApiCall: (Int) -> Unit,
    progressMax: Int = 60,
    onClear: () -> Unit,
    weatherDataBaseStates: List<BaseState<WeatherData>>,
) {
    BackHandler(onBack = onBack)
    var progress by rememberSaveable {
        mutableStateOf(0)
    }
    val progressBar by rememberProgressValue(
        visibility = progressVisibility,
        onProgressChange = { progress = it },
        initialValue = progress,
    )

    LaunchedEffect(progress, progressVisibility) {
        if (progressVisibility is ProgressVisibility.Loading && progress % 10 == 0 && progress < progressMax) {
            launchWeatherApiCall(progress)
        }
        if (progress == progressMax) {
            onProgressVisibilityChange(ProgressVisibility.Restart)
            progress = 0
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.title_weather))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    onProgressVisibilityChange(
                        ProgressVisibility.Loading,
                    )
                    onClear()
                },
                modifier = Modifier.alpha(if (progressVisibility is ProgressVisibility.Loading) .5f else 1f)
            ) {
                Text(text = stringResource(id = progressVisibility.textId))
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = modifier.padding(paddingValues),
        ) {
            if (progressVisibility !is ProgressVisibility.Restart) {
                ProgressBarWithText(
                    progress = (progressBar.toFloat() / progressMax),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    visibility = progressVisibility,
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(weatherDataBaseStates.size) { index ->
                        if (weatherDataBaseStates[index].message != null) {
                            Text(
                                text = weatherDataBaseStates[index].message ?: "message is nullable",
                                color = MaterialTheme.colorScheme.error,
                            )
                        } else if (weatherDataBaseStates[index].data != null) {
                            val data = weatherDataBaseStates[index].data!!
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                items(data.weathers.size) {
                                    WeatherCard(
                                        icon = data.weathers[it].icon,
                                        cityName = data.name,
                                        minTemp = data.tempMin.format(2).toString(),
                                        temp = data.temp.format(2).toString(),
                                        maxTemp = data.tempMax.format(2).toString(),
                                        description = data.weathers[it].description,
                                        modifier = Modifier
                                            .padding(20.dp)
                                            .fillMaxWidth(.8f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}