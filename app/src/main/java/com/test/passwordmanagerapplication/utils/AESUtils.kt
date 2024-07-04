package com.test.passwordmanagerapplication.utils

import android.util.Base64
import android.util.Log
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object AESUtils {
    private const val AES_MODE = "AES/CBC/PKCS5Padding"
    private const val AES_KEY_SIZE = 256

    // Generate a new AES key
    fun generateAESKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(AES_KEY_SIZE)
        return keyGenerator.generateKey()
    }


    // Encrypt plaintext using AES with provided key and IV
    fun encrypt(plaintext: String, key: SecretKey, iv: ByteArray): String {
        val cipher = Cipher.getInstance(AES_MODE)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)
        val encryptedBytes = cipher.doFinal(plaintext.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }


    // Decrypt ciphertext using AES with provided key and IV
    fun decrypt(encryptedData: String, secretKey: SecretKey, iv: ByteArray): String {
        return try {
            val cipher = Cipher.getInstance(AES_MODE)
            val ivSpec = IvParameterSpec(iv)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
            val decryptedBytes = cipher.doFinal(Base64.decode(encryptedData, Base64.DEFAULT))
            String(decryptedBytes)
        } catch (e: Exception) {
            //Log.e(TAG, "Error decrypting data", e)
            throw e
        }
    }

}