package com.example.shestore.di

import com.example.shestore.data.repository.FakeProductRepositoryImpl
import com.example.shestore.domain.repository.ProductRepository
import com.example.shestore.domain.usecase.GetFeaturedProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository = FakeProductRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetFeaturedProductsUseCase(
        repository: ProductRepository
    ): GetFeaturedProductsUseCase = GetFeaturedProductsUseCase(repository)
}