@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.list.create

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adewan.listmaker.common.navigation.AppNavigator
import com.adewan.listmaker.database.CoreListType
import com.adewan.listmaker.ui.common.capitalize

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateListScreen(navigator: AppNavigator, viewModel: CreateListViewModel = hiltViewModel()) {
    val focusManger = LocalFocusManager.current
    var listName by remember { mutableStateOf(TextFieldValue("")) }
    var listType by remember { mutableStateOf(CoreListType.GAMES) }
    var listNameInError by remember { mutableStateOf(false) }

    Scaffold(topBar = { CreateListTopBar(navigator = navigator) }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "Create list",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            OutlinedTextField(
                value = listName,
                onValueChange = {
                    listNameInError = it.text.isEmpty()
                    listName = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                maxLines = 1,
                singleLine = true,
                isError = listNameInError,
                label = {
                    Text(text = "List Name")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    focusManger.clearFocus()
                })
            )
            ListTypeDropDown(listType = listType) { listType = it }
            FilledTonalButton(
                onClick = {
                    if (listName.text.isEmpty()) {
                        listNameInError = true
                    } else {
                        viewModel.createList(title = listName.text, type = listType)
                        navigator.popCurrentRoute()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    "Save",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun ListTypeDropDown(listType: CoreListType, updateListType: (CoreListType) -> Unit) {
    var dropDownExpanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = dropDownExpanded,
        onExpandedChange = {
            dropDownExpanded = !dropDownExpanded
        },
        modifier = Modifier
            .padding(bottom = 15.dp)
    ) {
        OutlinedTextField(
            readOnly = true,
            value = capitalize(listType),
            onValueChange = { },
            label = { Text("List Type") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = dropDownExpanded
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = dropDownExpanded,
            onDismissRequest = {
                dropDownExpanded = false
            }
        ) {
            CoreListType.values().forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        updateListType(selectionOption)
                        dropDownExpanded = false
                    }, text = { Text(capitalize(selectionOption)) }
                )
            }
        }
    }
}


@Composable
private fun CreateListTopBar(navigator: AppNavigator) {
    SmallTopAppBar(navigationIcon = {
        Icon(
            Icons.Default.Close,
            contentDescription = "Close add list screen",
            modifier = Modifier
                .size(32.dp)
                .clickable { navigator.popCurrentRoute() }
        )
    }, title = {}, modifier = Modifier.padding(start = 10.dp))
}
