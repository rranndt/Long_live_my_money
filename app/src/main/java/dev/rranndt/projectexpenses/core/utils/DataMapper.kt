package dev.rranndt.projectexpenses.core.utils

import dev.rranndt.projectexpenses.data.local.entity.CategoryEntity
import dev.rranndt.projectexpenses.data.local.entity.ExpenseEntity
import dev.rranndt.projectexpenses.domain.model.Category
import dev.rranndt.projectexpenses.domain.model.Expense

object DataMapper {
    fun Category.toCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            categoryId = categoryId,
            name = name,
            colorValue = colorValue
        )
    }

    fun CategoryEntity.toCategory(): Category {
        return Category(
            categoryId = categoryId,
            name = name,
            colorValue = colorValue
        )
    }

    fun List<CategoryEntity>.toCategories(): List<Category> {
        return this.map {
            Category(
                categoryId = it.categoryId,
                name = it.name,
                colorValue = it.colorValue
            )
        }
    }

    fun Expense.toExpenseEntity(): ExpenseEntity {
        return ExpenseEntity(
            expenseId = expenseId,
            amount = amount,
            outputFlow = outputFlow,
            dateValue = dateValue,
            description = description,
            category = category?.toCategoryEntity()
        )
    }

    fun List<ExpenseEntity>.toExpenses(): List<Expense> {
        return this.map {
            Expense(
                expenseId = it.expenseId,
                amount = it.amount,
                outputFlow = it.outputFlow,
                dateValue = it.dateValue,
                description = it.description,
                category = it.category?.toCategory()
            )
        }
    }
}