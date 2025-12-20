package com.example.shestore.domain.usecase

import com.example.shestore.domain.model.Product
import com.example.shestore.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetFeaturedProductsUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> = repository.getFeaturedProducts()
}