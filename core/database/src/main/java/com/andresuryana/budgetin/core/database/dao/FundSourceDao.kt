package com.andresuryana.budgetin.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andresuryana.budgetin.core.database.entity.FundSourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FundSourceDao {

    @Query("SELECT * FROM fund_sources ORDER BY is_card DESC")
    fun getFundSources(): Flow<List<FundSourceEntity>>

    @Insert
    suspend fun insert(fundSource: FundSourceEntity)

    @Query("DELETE FROM fund_sources WHERE id = :id")
    suspend fun delete(id: String)
}