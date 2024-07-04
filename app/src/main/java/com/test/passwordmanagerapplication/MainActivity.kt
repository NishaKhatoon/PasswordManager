package com.test.passwordmanagerapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.test.passwordmanagerapplication.ui.HomeScreen
import com.test.passwordmanagerapplication.ui.NavGraph
import com.test.passwordmanagerapplication.ui.PasswordListScreen
import com.test.passwordmanagerapplication.ui.PasswordManagerScreen
import com.test.passwordmanagerapplication.ui.theme.PasswordManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordManagerTheme {
                //val navController = rememberNavController()
                //NavGraph(navController = navController)

                PasswordManagerScreen()
            }
        }
    }
}


