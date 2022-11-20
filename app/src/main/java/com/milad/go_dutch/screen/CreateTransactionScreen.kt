package com.milad.go_dutch.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.milad.core.data.Debtor
import com.milad.core.data.Group
import com.milad.core.data.TransactionType
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.data.groupList
import com.milad.go_dutch.data.list

@Composable
fun CreateTransactionScreen(navController: NavHostController) {
    val lazyListState = rememberLazyListState()

    Scaffold(topBar = { MyTopAppBar("Create Transaction") }) { padding ->
        createTransactionList(
            lazyListState = lazyListState,
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun Preview() {
    val context = LocalContext.current
    CreateTransactionScreen(navController = NavHostController(context))
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun createTransactionList(
    lazyListState: LazyListState,
    modifier: Modifier
) {
    var transactionName by remember { mutableStateOf(TextFieldValue("")) }
    var transactionNameCost by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) }
    var selectedType: TransactionType = TransactionType.EQUAL

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        state = lazyListState,
        modifier = modifier
    ) {
        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = transactionName,
                onValueChange = { value -> transactionName = value },
                label = { Text(text = "Transaction") },
                placeholder = { Text(text = "Enter Transaction name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = transactionNameCost,
                onValueChange = { value -> transactionNameCost = value },
                label = { Text(text = "Cost") },
                placeholder = { Text(text = "Enter Transaction cost") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = selectedType.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = "Transaction Type") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    TransactionType.values().forEach { transactionType ->
                        DropdownMenuItem(onClick = {
                            selectedType = transactionType
                            expanded = false
                        }) {
                            Text(text = transactionType.name)
                        }
                    }
                }
            }
        }
    }
}


