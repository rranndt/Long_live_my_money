package dev.rranndt.projectexpenses.data.local

import dev.rranndt.projectexpenses.data.local.db.ExpenseDao
import dev.rranndt.projectexpenses.data.local.entity.CategoryEntity
import dev.rranndt.projectexpenses.data.local.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: ExpenseDao,
) {
    suspend fun insertCategory(category: CategoryEntity) = dao.insertCategory(category)

    suspend fun deleteCategoryBy(id: Int) = dao.deleteCategoryBy(id)

    fun getCategories(): Flow<List<CategoryEntity>> = dao.getCategories()

    suspend fun insertExpense(expense: ExpenseEntity) = dao.insertExpense(expense)

    fun getExpenses(): Flow<List<ExpenseEntity>> = dao.getExpenses()
}