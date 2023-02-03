package fr.open.weather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.open.weather.R
import fr.open.weather.domain.model.ProgressVisibility
import kotlinx.coroutines.delay

@Composable
fun ProgressBarWithText(
    modifier: Modifier = Modifier,
    progress: Float,
    visibility: ProgressVisibility,
) {
    val stringArray = stringArrayResource(id = R.array.waiting_messages)
    var index by rememberSaveable { mutableStateOf(-1) }

    LaunchedEffect(visibility, index) {
        index = if (visibility is ProgressVisibility.Loading) {
            if (index == -1) index = 0
            delay(6_000)
            (index + 1) % stringArray.size
        } else {
            -1
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = if (index == -1) stringResource(id = R.string.start_downloading) else stringArray[index])
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressBarWithTextPreview() {
    var progress by remember {
        mutableStateOf(0f)
    }
    ProgressBarWithText(
        progress = progress,
        modifier = Modifier.fillMaxWidth(),
        visibility = ProgressVisibility.Loading,
    )

    LaunchedEffect(progress) {
        while (progress < 1f) {
            progress += .1f
            delay(30_000)
        }

    }

}