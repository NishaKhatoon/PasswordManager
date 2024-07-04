package com.test.passwordmanagerapplication.ui

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.test.passwordmanagerapplication.ui.theme.PasswordManagerTheme


@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    PasswordManagerTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}


@Preview(showBackground = true , uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview(){

    PasswordManagerTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}