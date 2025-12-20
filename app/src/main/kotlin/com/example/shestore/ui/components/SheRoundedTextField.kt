package com.example.shestore.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon         // ADDED
import androidx.compose.material3.IconButton   // ADDED
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource // ADDED
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.shestore.R

@Composable
fun SheRoundedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFB0B0B0)
            )
        },
        shape = RoundedCornerShape(50.dp),
        visualTransformation = if (isPassword && !isPasswordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        // --- FIX STARTS HERE ---
        trailingIcon = if (isPassword && onTogglePasswordVisibility != null) {
            {
                // We use IconButton to handle the click
                IconButton(onClick = onTogglePasswordVisibility) {
                    Icon(
                        // Load the image using painterResource
                        painter = painterResource(
                            id = if (isPasswordVisible) R.drawable.iconeyeclose else R.drawable.iconeyeopen
                        ),
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password",
                        // Optional: Tint the icon to match your theme or leave as Color.Unspecified for original colors
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        } else null,
        // --- FIX ENDS HERE ---
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F5F5),
            unfocusedContainerColor = Color(0xFFF5F5F5),
            disabledContainerColor = Color(0xFFF5F5F5),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground
        )
    )
}