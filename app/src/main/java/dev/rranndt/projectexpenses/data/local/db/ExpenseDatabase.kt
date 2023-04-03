package dev.rranndt.projectexpenses.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.rranndt.projectexpenses.data.local.converter.DateConverter
import dev.rranndt.projectexpenses.data.local.entity.CategoryEntity
import dev.rranndt.projectexpenses.data.local.entity.ExpenseEntity

@Database(
    entities = [CategoryEntity::class, ExpenseEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}