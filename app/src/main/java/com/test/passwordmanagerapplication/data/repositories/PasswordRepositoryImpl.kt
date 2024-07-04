package com.test.passwordmanagerapplication.data.repositories

import com.test.passwordmanagerapplication.data.PasswordDao
import com.test.passwordmanagerapplication.data.PasswordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasswordRepositoryImpl @Inject constructor(
    private val passwordDao: PasswordDao
) : PasswordRepository {
    override fun getAllPasswords(): Flow<List<PasswordEntity>> {
        return passwordDao.getAllPasswords()
    }

    override suspend fun insert(password: PasswordEntity) {
        passwordDao.insert(password)
    }

    override suspend fun update(password: PasswordEntity) {
        passwordDao.update(password)
    }

    override suspend fun delete(password: PasswordEntity) {
        passwordDao.delete(password)
    }

    override suspend fun getPasswordById(id: Int): PasswordEntity? {
        return passwordDao.getPasswordById(id)
    }


}