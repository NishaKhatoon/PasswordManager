@file:OptIn(ExperimentalMaterial3Api::class)

package com.test.passwordmanagerapplication.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.passwordmanagerapplication.ui.theme.PasswordManagerTheme
import com.test.passwordmanagerapplication.ui.theme.divider_color
import com.test.passwordmanagerapplication.ui.theme.floating_btn_color
import com.test.passwordmanagerapplication.ui.theme.text_color
import com.test.passwordmanagerapplication.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun PasswordListScreen() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    PasswordManagerTheme {
        Surface (modifier = Modifier.fillMaxSize() ,color = MaterialTheme.colorScheme.surface){
            Scaffold {
                // Floating Action Button
                FloatingActionButton(
                    onClick = { /* Handle FAB click */ },
                    modifier = Modifier
                        .padding(16.dp),  // Add padding for spacing
                    containerColor = floating_btn_color,
                    contentColor= white
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
            Box {
                Column {
                    // TopBar
                    TopAppBar(
                        title = {
                            Text("Password Manager",
                                color = text_color,
                                modifier = Modifier.padding(top = 35.dp),
                                style = MaterialTheme.typography.bodyMedium)

                                },
                    )

                    // Divider
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 10.dp),
                        color = divider_color
                    )

                    //Rest Screen
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PassWordListPreview(){
    PasswordManagerTheme {
        PasswordListScreen()
    }
}


