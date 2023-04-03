package dev.rranndt.projectexpenses.domain.usecase

import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseUseCaseImpl @Inject constructor(
    private val repository: ExpenseRepository,
) : ExpenseUseCase {
    override suspend fun insertCategory(category: Category) =
        repository.insertCategory(category)

    override suspend fun deleteCategoryBy(id: Int) =
        repository.deleteCategoryBy(id)

    override fun getCategories(): Flow<List<Category>> =
        repository.getCategories()

    override suspend fun insertExpense(expense: Expense) =
        repository.insertExpense(expense)

    override fun getExpenses(): Flow<List<Expense>> =
        repository.getExpenses()
}