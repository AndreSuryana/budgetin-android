package com.andresuryana.budgetin.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "expenses"
)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val notes: String?,
    val amount: Long,
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date,
    @ColumnInfo(name = "budget_id")
    val budgetId: String,
    @ColumnInfo(name = "category_id")
    val categoryId: String,
)
