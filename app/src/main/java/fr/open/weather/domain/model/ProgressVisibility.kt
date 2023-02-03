package fr.open.weather.domain.model

import androidx.annotation.StringRes
import fr.open.weather.R

sealed class ProgressVisibility(@StringRes val textId: Int) {
    object Start : ProgressVisibility(
        textId = R.string.start,
    )
    object Loading: ProgressVisibility(
        textId = R.string.loading
    )
    object Restart: ProgressVisibility(
        textId = R.string.restart
    )
}