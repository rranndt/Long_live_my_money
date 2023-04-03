package dev.rranndt.projectexpenses.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rranndt.projectexpenses.domain.usecase.ExpenseUseCase
import dev.rranndt.projectexpenses.domain.usecase.ExpenseUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun provideUseCase(
        useCase: ExpenseUseCaseImpl,
    ): ExpenseUseCase
}