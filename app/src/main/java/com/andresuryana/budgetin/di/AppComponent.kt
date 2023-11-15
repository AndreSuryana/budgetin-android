package com.andresuryana.budgetin.di

import com.andresuryana.budgetin.core.data.di.DataModule
import com.andresuryana.budgetin.core.database.di.DaoModule
import com.andresuryana.budgetin.core.database.di.DatabaseModule
import dagger.Component

@Component(
    modules = [
        DatabaseModule::class,
        DaoModule::class,
        DataModule::class,
    ]
)
interface AppComponent {

    // TODO: Need to do some research about 'multi-module dependency injection dagger hilt'
    // Because now the Module isn't recognized by dagger.
}