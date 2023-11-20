package com.andresuryana.budgetin.core.data.repository

import com.andresuryana.budgetin.core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    /**
     * Get user profile information.
     */
    fun getUserProfile(): Flow<User>
}