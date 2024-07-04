package com.test.passwordmanagerapplication.ui

sealed class NavDestination(val route: String) {

    data object PasswordList : NavDestination("passwordList")
    data object AddEditPassword : NavDestination("addEditPassword/{passwordId}") {
        fun createRoute(passwordId: Int) = "addEditPassword/$passwordId"
    }
}