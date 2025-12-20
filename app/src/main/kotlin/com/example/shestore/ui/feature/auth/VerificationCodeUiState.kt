package com.example.shestore.ui.feature.auth

data class VerificationCodeUiState(
    val code: String = "",
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null
)