package com.test.passwordmanagerapplication.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.test.passwordmanagerapplication.data.PasswordEntity
import com.test.passwordmanagerapplication.viewModels.PasswordViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditPasswordScreen(
    navController: NavHostController,
    viewModel: PasswordViewModel = hiltViewModel(),
    passwordId: Int
) {
    val isEditMode = passwordId != 0
    var accountType by remember { mutableStateOf("") }
    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scaffoldState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(passwordId) {
        if (isEditMode) {
            val passwordEntity = viewModel.getPasswordById(passwordId)
            passwordEntity?.let {
                accountType = it.accountType
                usernameOrEmail = it.usernameOrEmail
                password = it.password
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(scaffoldState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(if (isEditMode) "Edit Password" else "Add Password")
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = accountType,
                    onValueChange = { accountType = it },
                    label = { Text("Account Type") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = usernameOrEmail,
                    onValueChange = { usernameOrEmail = it },
                    label = { Text("Username/Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            if (accountType.isNotEmpty() && usernameOrEmail.isNotEmpty() && password.isNotEmpty()) {
                                val passwordEntity = PasswordEntity(
                                    id = if (isEditMode) passwordId else 0,
                                    accountType = accountType,
                                    usernameOrEmail = usernameOrEmail,
                                    password = password
                                )
                                if (isEditMode) {
                                    viewModel.updateAccount(passwordEntity)
                                } else {
                                    viewModel.insertAccount(passwordEntity)
                                }
                                navController.popBackStack()
                            } else {
                                coroutineScope.launch {
                                    scaffoldState.showSnackbar("Please fill all fields")
                                }
                            }
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    )
}