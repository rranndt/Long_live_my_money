package dev.rranndt.projectexpenses.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "expense")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expense_id")
    var expenseId: Int = 0,
    var amount: Double = 0.0,
    @ColumnInfo(name = "output_flow")
    var filter: String = "None",
    @ColumnInfo(name = "date_value")
    var dateValue: String = LocalDateTime.now().toString(),
    var description: String = "",
    @Embedded
    var category: CategoryEntity? = null,
)
