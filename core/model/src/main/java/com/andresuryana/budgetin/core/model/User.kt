package com.andresuryana.budgetin.core.model

data class User(
    val name: String,
    val email: String,
    val imageUrl: String? = null
)
