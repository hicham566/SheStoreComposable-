package com.example.shestore.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shestore.core.util.Resource
import com.example.shestore.domain.usecase.GetFeaturedProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedProductsUseCase: GetFeaturedProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        loadFeaturedProducts()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.Refresh -> loadFeaturedProducts()

            is HomeEvent.OnProductClick -> {
                // TODO: handle navigation to product details using event.productId
            }

            HomeEvent.OnSeeAllNewArrivals -> {
                // TODO: navigate to "All New Arrivals" screen or show full list
            }
        }
    }

    private fun loadFeaturedProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)
            try {
                getFeaturedProductsUseCase().collectLatest { products ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        featuredProducts = products,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = e.localizedMessage ?: "Unknown error"
                )
            }
        }
    }
}
