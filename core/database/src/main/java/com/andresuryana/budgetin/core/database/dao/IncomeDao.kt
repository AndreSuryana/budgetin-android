package com.andresuryana.budgetin.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.andresuryana.budgetin.core.database.entity.IncomeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {

    @Query("SELECT * FROM incomes WHERE fund_source_id = :fundSourceId")
    fun getIncomes(fundSourceId: String): Flow<List<IncomeEntity>>

    @Query("DELETE FROM incomes WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM incomes")
    suspend fun deleteAll()
}