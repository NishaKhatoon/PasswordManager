package com.test.passwordmanagerapplication.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PasswordStrengthMeter(password: String, showStrength: Boolean) {
    if (!showStrength) {
        return // Return early if strength should not be shown
    }
    val strength = when {
        password.length >= 12 && password.contains(Regex("[A-Z]")) && password.contains(Regex("[@#$%^&+=!]")) -> "Strong"
        password.length >= 8 && (password.contains(Regex("[A-Z]")) || password.contains(Regex("[@#$%^&+=!]"))) -> "Medium"
        password.length >= 6 -> "Weak"
        else -> "Very Weak"
    }
    val color = when (strength) {
        "Strong" -> Color.Green
        "Medium" -> Color.Yellow
        "Weak" -> Color.Red
        else -> Color.Gray
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = " Password Strength: $strength",
            color = color,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(12.dp)
        )
    }
}
