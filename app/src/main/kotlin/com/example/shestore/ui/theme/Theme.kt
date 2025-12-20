package com.example.shestore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Accent
)

private val DarkColors = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = AccentDark
)

@Composable
fun SheStoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}