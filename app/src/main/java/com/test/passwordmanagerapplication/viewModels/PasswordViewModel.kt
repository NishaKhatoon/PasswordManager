package com.test.passwordmanagerapplication.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.passwordmanagerapplication.data.PasswordEntity
import com.test.passwordmanagerapplication.data.repositories.PasswordRepository
import com.test.passwordmanagerapplication.utils.AESUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.crypto.SecretKey
import javax.inject.Inject


@HiltViewModel
class PasswordViewModel @Inject constructor(private val repository: PasswordRepository) : ViewModel(){

    private val _allPasswords = MutableStateFlow<List<PasswordEntity>>(emptyList())
    val allPasswords: StateFlow<List<PasswordEntity>> = _allPasswords
    val secretKey: SecretKey = AESUtils.generateAESKey()
    init {
        viewModelScope.launch {
            repository.getAllPasswords().collect { passwords ->
                _allPasswords.value = passwords
            }
        }
    }

    fun insertAccount(account: PasswordEntity) {
        //val originalText=account.password
        //val encryptedData = aesEncrypt(originalText.toByteArray(), secretKey)
        viewModelScope.launch {
            //val encryptedPassword = AESUtils.encrypt(account.password, secretKey, iv)
            //val encryptedPasswordEntity = account.copy(password = encryptedPassword)
            repository.insert(account)
        }
    }


    fun updateAccount(passwordEntity: PasswordEntity) {
       // val iv = ByteArray(16) // Generate or obtain IV securely
        viewModelScope.launch {
           // val encryptedPassword = AESUtils.encrypt(passwordEntity.password, secretKey, iv)
            //val encryptedPasswordEntity = passwordEntity.copy(password = encryptedPassword)
            repository.update(passwordEntity)
        }
    }

    fun deleteAccount(password: PasswordEntity) {
        viewModelScope.launch {
            repository.delete(password)
        }
    }

    suspend fun getPasswordById(id: Int): PasswordEntity? {
        return withContext(Dispatchers.IO) { //Context Switching
            repository.getPasswordById(id)
        }
    }
}