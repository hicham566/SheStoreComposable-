package com.example.shestore.data.repository

import com.example.shestore.domain.model.Product
import com.example.shestore.domain.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProductRepositoryImpl : ProductRepository {

    private val fakeProducts = listOf(
        Product(
            id = "1",
            name = "She Hoodie",
            description = "Oversized comfy hoodie from She-Store.",
            imageUrl = "",
            price = 39.99,
            isFeatured = true
        ),
        Product(
            id = "2",
            name = "She Sneakers",
            description = "Minimal sneakers to match your style.",
            imageUrl = "",
            price = 59.99,
            isFeatured = true
        ),
        Product(
            id = "3",
            name = "She Tote Bag",
            description = "Everyday tote bag for all your essentials.",
            imageUrl = "",
            price = 19.99,
            isFeatured = false
        )
    )

    override fun getFeaturedProducts(): Flow<List<Product>> = flow {
        emit(emptyList())
        delay(800)
        emit(fakeProducts.filter { it.isFeatured })
    }

    override fun getAllProducts(): Flow<List<Product>> = flow {
        emit(emptyList())
        delay(600)
        emit(fakeProducts)
    }

    override fun getProductById(id: String): Flow<Product?> = flow {
        emit(fakeProducts.find { it.id == id })
    }
}