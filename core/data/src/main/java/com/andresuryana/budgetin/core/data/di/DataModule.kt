package com.andresuryana.budgetin.core.data.di

import com.andresuryana.budgetin.core.data.fake.FakeUserNotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideUserNotificationRepository() = FakeUserNotificationRepository()
}