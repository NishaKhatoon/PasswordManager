package com.test.passwordmanagerapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.test.passwordmanagerapplication.R

val poppins= FontFamily(
    Font(R.font.poppins_bold,FontWeight.Bold),
    Font(R.font.poppins_semibold,FontWeight.SemiBold),
    Font(R.font.poppins_medium,FontWeight.Medium),
    Font(R.font.poppins_regular,FontWeight.Normal),
    Font(R.font.poppins_thin,FontWeight.Thin),
)

val TitleBlackTypography= TextStyle(
    fontFamily = poppins,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    color = text_color,
    textAlign = TextAlign.Left,
)

val TitlePassTypography= TextStyle(
    fontFamily = poppins,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    color = grey_color,
    textAlign = TextAlign.Left,
)

val TitleBlueTypography= TextStyle(
    fontFamily = poppins,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    color = floating_btn_color,
    textAlign = TextAlign.Left,
    )

val ErrorTypography = TextStyle(
    fontFamily = poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = input_box_error_color,
    textAlign = TextAlign.Left,

)

val LabelTypography = TextStyle(
    fontFamily = poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    textAlign = TextAlign.Left,
    )

val InputBoxTextTypography = TextStyle(
    fontFamily = poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = text_color,
    textAlign = TextAlign.Left,
)

val ButtonTextTypography = TextStyle(
    fontFamily = poppins,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    color = white,
    textAlign = TextAlign.Center,
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium =TextStyle(
         fontFamily = poppins,
         fontWeight = FontWeight.SemiBold,
         fontSize = 18.sp,
         lineHeight = 21.sp,
         letterSpacing = 0.5.sp
     ),

    bodySmall =TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)