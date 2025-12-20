package com.example.shestore.ui.feature.home

import com.example.shestore.domain.model.Product

data class HomeUiState(
    val isLoading: Boolean = false,
    val featuredProducts: List<Product> = emptyList(),
    val errorMessage: String? = null
)