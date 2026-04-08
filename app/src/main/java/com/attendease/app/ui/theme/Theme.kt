package com.attendease.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary      = BluePrimary,
    secondary    = PurpleMid,
    tertiary     = GreenSuccess,
    background   = BgLight,
    surface      = CardWhite,
    onPrimary    = Color.White,
    onBackground = TextDark,
    onSurface    = TextDark,
)

@Composable
fun AttendEaseTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography  = Typography,
        content     = content
    )
}
