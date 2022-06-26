@file:OptIn(ExperimentalMaterial3Api::class)

package com.adewan.listmaker.list.add

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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.adewan.listmaker.common.navigation.AppNavigatorImpl
import com.adewan.listmaker.models.ListType
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddListScreen(navigator: AppNavigatorImpl) {
    val focusManger = LocalFocusManager.current
    var listName by remember { mutableStateOf(TextFieldValue("")) }
    var dropDownExpanded by remember { mutableStateOf(false) }
    var listType by remember { mutableStateOf(ListType.GAMES) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Scaffold(topBar = { AddListTopBar(navigator = navigator) }) { paddingValues ->
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
                onValueChange = { listName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                maxLines = 1,
                singleLine = true,
                label = {
                    Text(text = "List Name")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    focusManger.clearFocus()
                })
            )
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
                    ListType.values().forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                listType = selectionOption
                                dropDownExpanded = false
                            }, text = { Text(capitalize(selectionOption)) }
                        )
                    }
                }
            }
            FilledTonalButton(
                onClick = { },
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text("Save", fontWeight = FontWeight.Bold)
            }
        }
    }
}

private fun capitalize(it: ListType) = it.name.lowercase(Locale.ROOT).replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.ROOT
    ) else it.toString()
}

@Composable
private fun AddListTopBar(navigator: AppNavigatorImpl) {
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