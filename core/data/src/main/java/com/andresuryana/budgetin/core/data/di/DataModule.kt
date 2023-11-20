package com.andresuryana.budgetin.core.data.di

import com.andresuryana.budgetin.core.data.fake.FakeUserNotificationRepository
import com.andresuryana.budgetin.core.data.fake.FakeUserRepository
import com.andresuryana.budgetin.core.data.repository.UserNotificationRepository
import com.andresuryana.budgetin.core.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideUserRepository(): UserRepository =
        FakeUserRepository()

    @Provides
    fun provideUserNotificationRepository(): UserNotificationRepository =
        FakeUserNotificationRepository()
}