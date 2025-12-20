package com.example.shestore.ui.feature.auth

data class RegisterUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null
)

