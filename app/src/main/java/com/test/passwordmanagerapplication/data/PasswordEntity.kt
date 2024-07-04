package com.test.passwordmanagerapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "account_type")
    val accountType: String,
    @ColumnInfo(name = "user_name_email")
    val usernameOrEmail: String ,
    @ColumnInfo(name = "password")
    val password: String)
