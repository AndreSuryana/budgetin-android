package com.andresuryana.budgetin.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "categories"
)
data class CategoryEntity(
    @PrimaryKey
    val id: String, // Use field 'title' as id but in lowercase
    val title: String,
    val icon: String,
    val color: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date,
)