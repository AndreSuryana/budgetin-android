package com.andresuryana.budgetin.core.database.di

import android.content.Context
import androidx.room.Room
import com.andresuryana.budgetin.core.database.BudgetinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBudgetinDatabase(
        @ApplicationContext context: Context,
    ): BudgetinDatabase = Room.databaseBuilder(
        context,
        BudgetinDatabase::class.java,
        "budgetin_database"
    ).build()
}