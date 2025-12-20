package com.example.shestore.ui.feature.auth

data class LocationUiState(
    val countryName: String = "Morocco",
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null
)