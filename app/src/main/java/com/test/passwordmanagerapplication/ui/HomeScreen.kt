package com.test.passwordmanagerapplication.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.passwordmanagerapplication.data.PasswordEntity
import com.test.passwordmanagerapplication.ui.theme.PasswordManagerTheme
import com.test.passwordmanagerapplication.ui.theme.text_color
import com.test.passwordmanagerapplication.viewModels.PasswordViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(passwordViewModel: PasswordViewModel = hiltViewModel()){

    val passwordList by passwordViewModel.allPasswords.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title =
            {  Text(
                text = "Password Manager",
                color = text_color,  // Set text color
                modifier = Modifier.padding(top = 40.dp),  // Set top margin
                style = MaterialTheme.typography.bodyMedium // Set text style if needed
            )
            })
        }
        ,
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Handle Add New Account */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        Surface {
            LazyColumn {
                items(passwordList) { password ->
                    PasswordItem(password)
                }
            }
        }

    }
}

@Composable
fun PasswordItem(password: PasswordEntity) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = password.accountType)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Password: ${"*".repeat(password.password.length)}")
            }
            IconButton(onClick = { /* Handle item click */ }) {
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "View Details")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    PasswordManagerTheme {
        HomeScreen()
    }
}

