package fr.open.weather.presentation.components.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import fr.open.weather.domain.model.ProgressVisibility
import kotlinx.coroutines.delay

@Composable
fun rememberProgressValue(
    initialValue: Int = 0,
    visibility: ProgressVisibility,
    onProgressChange: (Int) -> Unit,
): State<Int> {
    return produceState(initialValue = initialValue, visibility) {
        while (visibility is ProgressVisibility.Loading) {
            delay(1_000)
            onProgressChange(value++)
        }
        value = 0
    }
}