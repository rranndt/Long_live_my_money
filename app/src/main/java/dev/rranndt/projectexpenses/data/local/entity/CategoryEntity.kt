package dev.rranndt.projectexpenses.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "category",
    indices = [
        Index("name", unique = true)
    ]
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("category_id")
    var categoryId: Int = 0,
    var name: String = "",
    @ColumnInfo("color_value")
    var colorValue: String = "0,0,0",
)