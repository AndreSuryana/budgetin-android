package com.andresuryana.budgetin.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andresuryana.budgetin.core.database.entity.BudgetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    @Query("SELECT * FROM budgets WHERE month = :month AND year = :year")
    fun getBudgets(month: Int, year: Int): Flow<List<BudgetEntity>>

    @Insert
    suspend fun insert(budget: BudgetEntity)

    @Query("DELETE FROM budgets WHERE id = :id")
    suspend fun delete(id: String)
}