package dev.rranndt.projectexpenses.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.rranndt.projectexpenses.data.local.entity.CategoryEntity
import dev.rranndt.projectexpenses.data.local.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Query("DELETE FROM category WHERE category_id = :id")
    suspend fun deleteCategoryBy(id: Int)

    @Query("SELECT * FROM category ORDER BY category_id ASC")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expense ORDER BY date_value ASC")
    fun getExpenses(): Flow<List<ExpenseEntity>>
}