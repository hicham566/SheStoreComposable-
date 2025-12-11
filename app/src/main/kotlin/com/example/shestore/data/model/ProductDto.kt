package com.example.shestore.data.model

import com.example.shestore.domain.model.Product

data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double,
    val isFeatured: Boolean
)

fun ProductDto.toDomain(): Product = Product(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    price = price,
    isFeatured = isFeatured
)