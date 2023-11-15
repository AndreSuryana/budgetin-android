package com.andresuryana.budgetin.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "incomes"
)
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val description: String?,
    val amount: Long,
    @ColumnInfo(name = "fund_source_id")
    val fundSourceId: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date,
)
