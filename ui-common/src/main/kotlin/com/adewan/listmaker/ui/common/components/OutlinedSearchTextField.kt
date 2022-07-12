@file:OptIn(ExperimentalComposeUiApi::class)

package com.adewan.listmaker.ui.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedSearchTextField(
    modifier: Modifier = Modifier,
    searchValue: TextFieldValue,
    placeholder: String,
    updateValue: (TextFieldValue) -> Unit,
    executeSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = searchValue,
        onValueChange = { updateValue(it) },
        modifier = modifier.fillMaxWidth().padding(bottom = 15.dp).padding(horizontal = 15.dp),
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(placeholder) },
        shape = RoundedCornerShape(20.dp),
        trailingIcon = { Icon(Icons.Default.Search, contentDescription = "") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions =
            KeyboardActions(
                onSearch = {
                    executeSearch()
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
        colors = TextFieldDefaults.outlinedTextFieldColors()
    )
}
