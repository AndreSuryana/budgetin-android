package com.andresuryana.budgetin.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "fund_sources"
)
data class FundSourceEntity(
    @PrimaryKey
    val id: String, // Use field 'name' as id but in lowercase
    val name: String,
    @ColumnInfo(name = "is_card")
    val isCard: Boolean,
    val icon: String,
    val color: String,
)
