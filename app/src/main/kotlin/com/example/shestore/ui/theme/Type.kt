package com.example.shestore.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shestore.R

// 1. Define the FontFamily
val Montserrat = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_regular, FontWeight.Medium)


)

// 2. Apply it to your Typography
val Typography = Typography(
    // Headline (Large text like "New Account")
    headlineMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp
    ),
    // Body (Normal text)
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    // Button text
    labelLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp
    ),



    /* You can override other styles (titleLarge, displaySmall, etc.) similarly */
)