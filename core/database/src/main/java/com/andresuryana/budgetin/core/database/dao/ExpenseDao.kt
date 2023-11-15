package com.andresuryana.budgetin.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.andresuryana.budgetin.core.database.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query(
        "SELECT * FROM expenses WHERE " +
                "(:budgetId IS NULL OR budget_id = :budgetId) AND " +
                "(:categoryId IS NULL OR category_id = :categoryId)"
    )
    fun getExpenses(budgetId: String?, categoryId: String?): Flow<List<ExpenseEntity>>

    @Query("SELECT SUM(amount) FROM expenses WHERE budget_id = :budgetId")
    fun getExpenseTotal(budgetId: String): Flow<Long>

    @Insert
    suspend fun insert(expense: ExpenseEntity)

    @Update
    suspend fun update(expense: ExpenseEntity)

    @Query("DELETE FROM expenses WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM expenses")
    suspend fun deleteAll()
}