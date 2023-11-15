package com.andresuryana.budgetin.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andresuryana.budgetin.core.database.converter.DateConverter
import com.andresuryana.budgetin.core.database.dao.BudgetDao
import com.andresuryana.budgetin.core.database.dao.CategoryDao
import com.andresuryana.budgetin.core.database.dao.ExpenseDao
import com.andresuryana.budgetin.core.database.dao.FundSourceDao
import com.andresuryana.budgetin.core.database.dao.IncomeDao
import com.andresuryana.budgetin.core.database.dao.NotificationDao
import com.andresuryana.budgetin.core.database.dao.RecentSearchQueryDao
import com.andresuryana.budgetin.core.database.entity.BudgetEntity
import com.andresuryana.budgetin.core.database.entity.CategoryEntity
import com.andresuryana.budgetin.core.database.entity.ExpenseEntity
import com.andresuryana.budgetin.core.database.entity.FundSourceEntity
import com.andresuryana.budgetin.core.database.entity.IncomeEntity
import com.andresuryana.budgetin.core.database.entity.NotificationEntity
import com.andresuryana.budgetin.core.database.entity.RecentSearchQueryEntity

@Database(
    entities = [
        BudgetEntity::class,
        CategoryEntity::class,
        FundSourceEntity::class,
        IncomeEntity::class,
        ExpenseEntity::class,
        NotificationEntity::class,
        RecentSearchQueryEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    DateConverter::class,
)
abstract class BudgetinDatabase : RoomDatabase() {
    abstract fun budgetDao(): BudgetDao
    abstract fun categoryDao(): CategoryDao
    abstract fun fundSourceDao(): FundSourceDao
    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun notificationDao(): NotificationDao
    abstract fun recentSearchQueryDao(): RecentSearchQueryDao
}