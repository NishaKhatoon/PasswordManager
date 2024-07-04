package com.test.passwordmanagerapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.test.passwordmanagerapplication.R
import com.test.passwordmanagerapplication.data.PasswordEntity
import com.test.passwordmanagerapplication.ui.theme.ButtonTextTypography
import com.test.passwordmanagerapplication.ui.theme.ErrorTypography
import com.test.passwordmanagerapplication.ui.theme.InputBoxTextTypography
import com.test.passwordmanagerapplication.ui.theme.LabelTypography
import com.test.passwordmanagerapplication.ui.theme.TitleBlueTypography
import com.test.passwordmanagerapplication.ui.theme.button_bg_delete_color
import com.test.passwordmanagerapplication.ui.theme.button_bg_primary_color
import com.test.passwordmanagerapplication.ui.theme.button_stroke_color
import com.test.passwordmanagerapplication.ui.theme.input_box_error_color
import com.test.passwordmanagerapplication.ui.theme.input_box_focused_color
import com.test.passwordmanagerapplication.ui.theme.input_box_unfocused_color
import com.test.passwordmanagerapplication.utils.AESUtils
import com.test.passwordmanagerapplication.utils.BottomSheetMode
import com.test.passwordmanagerapplication.utils.PasswordStrengthMeter
import kotlinx.coroutines.Job
import javax.crypto.SecretKey


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountSheetDialog(
    mode: BottomSheetMode,
    account: PasswordEntity? = null,
    onDismiss: () -> Unit,
    onSave: (PasswordEntity) -> Unit,
    onDelete: (PasswordEntity) -> Unit,
    function: () -> Job

){
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ){
        val context = LocalContext.current

        val validateAccountType: (String) -> Boolean = { it.isNotEmpty() }
        val validateUserNameOrEmail: (String) -> Boolean = { it.isNotEmpty() }
        val validatePassword: (String) -> Boolean = { password ->
            val regex = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+\$).{6,}\$")
            password.matches(regex)
        }

        var currentAccountType by remember { mutableStateOf(account?.accountType ?: "") }
        var currentUserNameOrEmail by remember { mutableStateOf(account?.usernameOrEmail ?: "") }
        var currentPassword by remember { mutableStateOf(account?.password ?: "") }

        var showPasswordStrength by remember { mutableStateOf(false) } // State to manage visibility of password strength
        var showPassword by remember { mutableStateOf(value = false) }

        var isAccountTypeValid by remember { mutableStateOf(true) }
        var isUserNameOrEmailValid by remember { mutableStateOf(true) }
        var isPasswordValid by remember { mutableStateOf(true) }

        Surface {
            Column( modifier = Modifier
                .fillMaxWidth()
            ){

                /*---Top Title----*/

                Text(
                    text = if (mode == BottomSheetMode.UPDATE) stringResource(id = R.string.account_details) else {""},
                    style = TitleBlueTypography,
                    modifier = Modifier.padding(start = 15.dp, bottom = 5.dp)
                )

                /*--Account Type Field----*/

                OutlinedTextField(
                    value = currentAccountType,
                    onValueChange = {
                        currentAccountType = it
                        isAccountTypeValid = validateAccountType(it)
                    },
                    label = { Text(
                        text = context.getString(R.string.account_type),
                        style = LabelTypography
                    ) },
                    isError = !isAccountTypeValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textStyle = InputBoxTextTypography,
                    colors = OutlinedTextFieldDefaults.colors(

                        unfocusedTextColor = input_box_unfocused_color,
                        unfocusedBorderColor = input_box_unfocused_color,
                        unfocusedLabelColor = input_box_unfocused_color,
                        unfocusedLeadingIconColor = input_box_unfocused_color,

                        focusedBorderColor = input_box_focused_color,
                        focusedTextColor = input_box_focused_color,
                        focusedLabelColor = input_box_focused_color,
                        focusedLeadingIconColor = input_box_focused_color,

                        errorBorderColor = input_box_error_color,
                        errorCursorColor = input_box_error_color,

                        cursorColor = input_box_focused_color

                    ),
                )
                if (!isAccountTypeValid) {
                    Text(text = String.format(stringResource(R.string.required_input),stringResource(R.string.account_type)),
                          style  = ErrorTypography,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                /*--User Name Email Id---*/

                OutlinedTextField(
                    value = currentUserNameOrEmail,
                    onValueChange = {
                        currentUserNameOrEmail = it
                        isUserNameOrEmailValid = validateUserNameOrEmail(it)
                    },
                    label = { Text(
                        text = stringResource(R.string.user_name_email),
                        style = LabelTypography
                    ) },
                    isError = !isUserNameOrEmailValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textStyle = InputBoxTextTypography,
                    colors = OutlinedTextFieldDefaults.colors(

                        unfocusedTextColor = input_box_unfocused_color,
                        unfocusedBorderColor = input_box_unfocused_color,
                        unfocusedLabelColor = input_box_unfocused_color,
                        unfocusedLeadingIconColor = input_box_unfocused_color,

                        focusedBorderColor = input_box_focused_color,
                        focusedTextColor = input_box_focused_color,
                        focusedLabelColor = input_box_focused_color,
                        focusedLeadingIconColor = input_box_focused_color,

                        errorBorderColor = input_box_error_color,
                        errorCursorColor = input_box_error_color,

                        cursorColor = input_box_focused_color

                    ),
                )
                if (!isUserNameOrEmailValid) {
                    Text(text = String.format(stringResource(R.string.required_input),stringResource(R.string.user_name_email)),
                        style  = ErrorTypography,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                /*--User Password --*/

                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = {newText ->
                        currentPassword = newText
                        isPasswordValid = validatePassword(newText)
                        showPasswordStrength = newText.isNotEmpty() // Show strength meter when password is not empty
                    },
                    label = { Text(
                        text = stringResource(R.string.password),
                        style = LabelTypography
                    ) },
                    isError = !isUserNameOrEmailValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textStyle = InputBoxTextTypography,
                    colors = OutlinedTextFieldDefaults.colors(

                        unfocusedTextColor = input_box_unfocused_color,
                        unfocusedBorderColor = input_box_unfocused_color,
                        unfocusedLabelColor = input_box_unfocused_color,
                        unfocusedLeadingIconColor = input_box_unfocused_color,

                        focusedBorderColor = input_box_focused_color,
                        focusedTextColor = input_box_focused_color,
                        focusedLabelColor = input_box_focused_color,
                        focusedLeadingIconColor = input_box_focused_color,

                        errorBorderColor = input_box_error_color,
                        errorCursorColor = input_box_error_color,

                        cursorColor = input_box_focused_color
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon ={
                        if (showPassword) {
                            IconButton(onClick = { showPassword = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "show_password"
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { showPassword = true }) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "hide_password"
                                )
                            }
                        }
                    }
                )

                if (!isPasswordValid) {
                    Text(
                        text = "Password must be at least 6 characters, contain one uppercase letter, one special character (@#$%^&+=!), and no spaces",
                        style = ErrorTypography,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }

                PasswordStrengthMeter(password = currentPassword, showStrength = showPasswordStrength)

                Spacer(modifier = Modifier.height(10.dp))

                if(mode==BottomSheetMode.ADD){
                    OutlinedButton(
                        onClick = {
                            val isFormValid = validateAccountType(currentAccountType) &&
                                    validateUserNameOrEmail(currentUserNameOrEmail) &&
                                    validatePassword(currentPassword)

                            isAccountTypeValid = validateAccountType(currentAccountType)
                            isUserNameOrEmailValid = validateUserNameOrEmail(currentUserNameOrEmail)
                            isPasswordValid = validatePassword(currentPassword)

                            if (isFormValid) {
                                // Handle form submission


                                val passwordEntity =  PasswordEntity(
                                    accountType = currentAccountType,
                                    usernameOrEmail = currentUserNameOrEmail,
                                    password = currentPassword)

                                onSave(passwordEntity)
                                onDismiss()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                        ,
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor= button_bg_primary_color
                        ),
                        border = BorderStroke(1.dp,button_stroke_color)

                    ) {
                        Text(
                            text = stringResource(id = R.string.add_new_account),
                            style=ButtonTextTypography
                        )
                    }
                }
                else if(mode==BottomSheetMode.UPDATE){

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        /*----Edit Button----*/
                        OutlinedButton(
                            onClick = {
                                val isFormValid = validateAccountType(currentAccountType) &&
                                        validateUserNameOrEmail(currentUserNameOrEmail) &&
                                        validatePassword(currentPassword)

                                isAccountTypeValid = validateAccountType(currentAccountType)
                                isUserNameOrEmailValid = validateUserNameOrEmail(currentUserNameOrEmail)
                                isPasswordValid = validatePassword(currentPassword)

                                if (isFormValid) {
                                    // Handle form submission
                                    val passwordEntity = account?.copy(
                                        accountType = currentAccountType,
                                        usernameOrEmail = currentUserNameOrEmail,
                                        password = currentPassword
                                    ) ?: PasswordEntity(
                                        accountType = currentAccountType,
                                        usernameOrEmail = currentUserNameOrEmail,
                                        password = currentPassword
                                    )
                                    onSave(passwordEntity)
                                    onDismiss()
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                            ,
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor= button_bg_primary_color
                            ),
                            border = BorderStroke(1.dp,button_stroke_color)

                        ) {
                            Text(
                                text = stringResource(id = R.string.edit),
                                style=ButtonTextTypography
                            )
                        }

                        /*----Delete Button----*/

                        OutlinedButton(
                            onClick = {
                                if (account != null){
                                    // Handle form submission
                                    onDelete(account)
                                    onDismiss()
                                }

                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                            ,
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor= button_bg_delete_color
                            ),
                            border = BorderStroke(1.dp,button_stroke_color)

                        ) {
                            Text(
                                text = stringResource(id = R.string.delete),
                                style=ButtonTextTypography
                            )
                        }
                    }
                }


            }//Ending Column
        }

    }

}