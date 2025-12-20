package com.example.shestore.domain.repository

import com.example.shestore.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getFeaturedProducts(): Flow<List<Product>>
    fun getAllProducts(): Flow<List<Product>>
    fun getProductById(id: String): Flow<Product?>
}