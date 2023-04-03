package dev.rranndt.projectexpenses.data.repository

import dev.rranndt.projectexpenses.core.utils.DataMapper.toCategories
import dev.rranndt.projectexpenses.core.utils.DataMapper.toCategoryEntity
import dev.rranndt.projectexpenses.core.utils.DataMapper.toExpenseEntity
import dev.rranndt.projectexpenses.core.utils.DataMapper.toExpenses
import dev.rranndt.projectexpenses.data.local.LocalDataSource
import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.domain.model.Expense
import dev.rranndt.projectexpenses.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : ExpenseRepository {
    override suspend fun insertCategory(category: Category) =
        localDataSource.insertCategory(category.toCategoryEntity())

    override suspend fun deleteCategoryBy(id: Int) =
        localDataSource.deleteCategoryBy(id)

    override fun getCategories(): Flow<List<Category>> =
        localDataSource.getCategories().map { it.toCategories() }

    override suspend fun insertExpense(expense: Expense) =
        localDataSource.insertExpense(expense.toExpenseEntity())

    override fun getExpenses(): Flow<List<Expense>> =
        localDataSource.getExpenses().map { it.toExpenses() }
}