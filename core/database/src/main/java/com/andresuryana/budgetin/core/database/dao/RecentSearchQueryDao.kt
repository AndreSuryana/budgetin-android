package com.andresuryana.budgetin.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andresuryana.budgetin.core.database.entity.RecentSearchQueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchQueryDao {

    @Query("SELECT * FROM recent_search_queries ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentSearchQueries(limit: Int): Flow<List<RecentSearchQueryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(recentSearchQuery: RecentSearchQueryEntity)

    @Query("DELETE FROM recent_search_queries WHERE search_query = :query")
    suspend fun delete(query: String)
}