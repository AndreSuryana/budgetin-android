package com.andresuryana.budgetin.core.database.di

import com.andresuryana.budgetin.core.database.BudgetinDatabase
import com.andresuryana.budgetin.core.database.dao.BudgetDao
import com.andresuryana.budgetin.core.database.dao.CategoryDao
import com.andresuryana.budgetin.core.database.dao.ExpenseDao
import com.andresuryana.budgetin.core.database.dao.FundSourceDao
import com.andresuryana.budgetin.core.database.dao.IncomeDao
import com.andresuryana.budgetin.core.database.dao.NotificationDao
import com.andresuryana.budgetin.core.database.dao.RecentSearchQueryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideBudgetDao(database: BudgetinDatabase): BudgetDao =
        database.budgetDao()

    @Provides
    fun provideCategoryDao(database: BudgetinDatabase): CategoryDao =
        database.categoryDao()

    @Provides
    fun provideFundSourceDao(database: BudgetinDatabase): FundSourceDao =
        database.fundSourceDao()

    @Provides
    fun provideIncomeDao(database: BudgetinDatabase): IncomeDao =
        database.incomeDao()

    @Provides
    fun provideExpenseDao(database: BudgetinDatabase): ExpenseDao =
        database.expenseDao()

    @Provides
    fun provideNotificationDao(database: BudgetinDatabase): NotificationDao =
        database.notificationDao()

    @Provides
    fun provideRecentSearchQueryDao(database: BudgetinDatabase): RecentSearchQueryDao =
        database.recentSearchQueryDao()
}