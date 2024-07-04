package com.test.passwordmanagerapplication.data.repositories

import com.test.passwordmanagerapplication.data.PasswordEntity
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {

    fun getAllPasswords(): Flow<List<PasswordEntity>>
    suspend fun insert(password: PasswordEntity)
    suspend fun update(password: PasswordEntity)
    suspend fun delete(password: PasswordEntity)
    suspend fun getPasswordById(id: Int): PasswordEntity?
}