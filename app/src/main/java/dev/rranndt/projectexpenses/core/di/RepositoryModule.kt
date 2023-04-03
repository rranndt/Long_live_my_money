package dev.rranndt.projectexpenses.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rranndt.projectexpenses.data.repository.ExpenseRepositoryImpl
import dev.rranndt.projectexpenses.domain.repository.ExpenseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(
        repository: ExpenseRepositoryImpl,
    ): ExpenseRepository
}