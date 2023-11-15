package com.andresuryana.budgetin.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "recent_search_queries"
)
data class RecentSearchQueryEntity(
    @PrimaryKey
    @ColumnInfo(name = "search_query")
    val searchQuery: String,
    val timestamp: Long,
)
