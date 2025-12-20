package com.example.shestore.ui.feature.auth

data class PhoneNumberUiState(
    val phone: String = "",
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null
)
