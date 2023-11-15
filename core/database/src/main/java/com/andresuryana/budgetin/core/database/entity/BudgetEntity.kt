package com.andresuryana.budgetin.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "budgets"
)
data class BudgetEntity(
    @PrimaryKey
    val id: String, // Format: "userUID-month-year", ex: "user123-11-2023"
    val month: Int,
    val year: Int,
    val limit: Long,
    @ColumnInfo(name = "weekly_budget")
    val weeklyBudget: Long?, // When specified, it means weekly budget is enabled
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date,
)
