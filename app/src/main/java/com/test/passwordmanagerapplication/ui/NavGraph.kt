package com.test.passwordmanagerapplication.ui



import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.passwordmanagerapplication.viewModels.PasswordViewModel


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavDestination.PasswordList.route) {
        composable(route = NavDestination.PasswordList.route) {
            //PasswordManagerScreen(navController = navController)
        }
        composable(route = NavDestination.AddEditPassword.route) { backStackEntry ->
            val passwordId = backStackEntry.arguments?.getString("passwordId")?.toInt() ?: 0
            val viewModel: PasswordViewModel = hiltViewModel()
            AddEditPasswordScreen(navController = navController, viewModel = viewModel, passwordId = passwordId)
        }

    }
}
