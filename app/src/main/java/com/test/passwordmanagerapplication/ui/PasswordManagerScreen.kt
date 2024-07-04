package com.test.passwordmanagerapplication.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.test.passwordmanagerapplication.R
import com.test.passwordmanagerapplication.data.PasswordEntity
import com.test.passwordmanagerapplication.ui.theme.PasswordManagerTheme
import com.test.passwordmanagerapplication.ui.theme.TitleBlackTypography
import com.test.passwordmanagerapplication.ui.theme.TitlePassTypography
import com.test.passwordmanagerapplication.ui.theme.card_stroke_color
import com.test.passwordmanagerapplication.ui.theme.divider_color
import com.test.passwordmanagerapplication.ui.theme.floating_btn_color
import com.test.passwordmanagerapplication.ui.theme.text_color
import com.test.passwordmanagerapplication.ui.theme.white
import com.test.passwordmanagerapplication.utils.AESUtils
import com.test.passwordmanagerapplication.utils.BottomSheetMode
import com.test.passwordmanagerapplication.viewModels.PasswordViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.crypto.SecretKey


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerScreen(viewModel: PasswordViewModel = hiltViewModel()) {
    val passwords by viewModel.allPasswords.collectAsState(initial = emptyList())
    var showSheet by remember { mutableStateOf(false) }
    var mode by remember { mutableStateOf(BottomSheetMode.ADD) }
    var currentPassword by remember { mutableStateOf<PasswordEntity?>(null) }
    val scope = rememberCoroutineScope()

    if (showSheet) {
        AddAccountSheetDialog(
            mode = mode,
            account = currentPassword,
            onDismiss = { showSheet = false },
            onSave = { password ->
                if (mode == BottomSheetMode.ADD) {

                    viewModel.insertAccount(password)
                } else {
                    viewModel.updateAccount(password)
                }
                showSheet = false
            },
            onDelete = { password ->
                viewModel.deleteAccount(password)
                showSheet = false
            }
        ) {
            scope.launch {
                showSheet = false
            }

        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
        )
    {
        Scaffold(
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)

                ){
                    Text(text = stringResource(id = R.string.password_manager),
                        modifier = Modifier.padding(top = 40.dp , start = 20.dp),
                        style = TitleBlackTypography)

                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 10.dp),
                        color = divider_color
                    )  // Horizontal divider

                    Spacer(modifier = Modifier.height(5.dp))

                    LazyColumn(modifier = Modifier.padding(top = 20.dp , bottom = 20.dp, start = 10.dp, end = 10.dp)) {
                        items(passwords) { password ->
                            PasswordItem(
                                passwordEntity = password,
                                onEdit = {
                                    //calling dialog with data
                                    scope.launch {
                                        mode = BottomSheetMode.UPDATE
                                        currentPassword = password
                                        showSheet = true
                                    }
                                }
                            )
                        }
                    }



                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        mode = BottomSheetMode.ADD
                        currentPassword = null
                        showSheet = true
                    },
                    modifier = Modifier
                        .padding(16.dp),  // Add padding for spacing
                    containerColor = floating_btn_color,
                    contentColor= white)
                {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        )
    }



}

@Composable
fun PasswordItem(
    passwordEntity: PasswordEntity,
    onEdit: () -> Unit
) {
    //val decryptedData = aesDecrypt(passwordEntity.password.toByteArray(), secretKey)
    //val decryptedPassword = rememberDecryptPassword(passwordEntity.password, secretKey)

    Card(
        shape = RoundedCornerShape(50.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp , horizontal = 5.dp)
            .clickable { onEdit() },
        border = BorderStroke(1.dp, card_stroke_color), // Border stroke width and color
        colors = CardDefaults.cardColors(
            containerColor = white,
        ),

    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(text = passwordEntity.accountType,
                style = TitleBlackTypography,
                modifier = Modifier.padding(10.dp)
                )

            Text(
                text = "******",
                style = TitlePassTypography,
                modifier = Modifier.padding(10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEdit) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "right",
                        tint = Color.Black)
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun rememberDecryptPassword(encryptedPassword: String, secretKey: SecretKey): String {
    val coroutineScope = rememberCoroutineScope()
    var decryptedPassword by remember { mutableStateOf("") }

    coroutineScope.launch {
        decryptedPassword = decryptPassword(encryptedPassword, secretKey)
    }

    return decryptedPassword
}

private suspend fun decryptPassword(encryptedPassword: String, secretKey: SecretKey): String {
    return withContext(Dispatchers.Default) {
        val iv = ByteArray(16) // Use the correct IV for decryption
        AESUtils.decrypt(encryptedPassword, secretKey, iv)
    }
}

fun maskPassword(decryptedPassword: String): String {
    val maskedLength = decryptedPassword.length - 4
    val maskedPart = "*".repeat(maskedLength)
    return "${decryptedPassword.take(4)}$maskedPart"

}


@Preview(showBackground = true)
@Composable
fun PasswordListPreview(){
    PasswordManagerTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}