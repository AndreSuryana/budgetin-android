package com.andresuryana.budgetin.core.data.fake

import com.andresuryana.budgetin.core.data.repository.UserRepository
import com.andresuryana.budgetin.core.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserRepository : UserRepository {

    override fun getUserProfile(): Flow<User> =
        flowOf(User("Andre Suryana", "Andresuryana17@gmail.com"))
}