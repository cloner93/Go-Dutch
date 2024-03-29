package com.milad.go_dutch.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.milad.core.data.*
import com.milad.core.data.TransactionType.*
import com.milad.core.type.Transaction2
import com.milad.core.type.Type
import com.milad.core.type.TypeEqual
import com.milad.core.type.TypePercent
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.data.groupList

@Composable
fun CreateTransactionScreen(navController: NavHostController, index: String) {
    val group = groupList[index.toInt()]

    Scaffold(topBar = { MyTopAppBar("Create Transaction") }) { padding ->
        CreateTransactionList(
            group = group,
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
        ) { transaction: Type ->

            groupList[index.toInt()].transactions.add(
                transaction
            )
            navController.popBackStack()
        }
    }
}

@Preview
@Composable
private fun CreateTransactionPreview() {
    CreateTransactionScreen(navController = rememberNavController(), index = "0")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CreateTransactionList(
    group: Group,
    modifier: Modifier,
    onClick: (Type) -> Boolean
) {
    val list = remember { mutableStateMapOf<Debtor, Float>() }

    val transactionName = remember { TextFieldState("") }
    val transactionNameCost = remember { TextFieldState("") }

    var transactionTypeMenuExpanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(EQUAL) }

    var debtors: PayType? = null

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        GoTextField(
            labelText = "Transaction",
            placeholderText = "Enter Transaction name",
            keyboardType = KeyboardType.Text,
            value = transactionName
        )

        GoTextField(
            labelText = "Cost",
            placeholderText = "Enter Transaction cost",
            keyboardType = KeyboardType.Decimal,
            value = transactionNameCost
        )

        Text(text = "Payers:")
        for (item in list) {
            ListItem(item.key, item.value)
        }

        AddPayer(group) { debtor, cost ->
            list[debtor] = cost
        }

        ExposedDropdownMenuBox(expanded = transactionTypeMenuExpanded, onExpandedChange = {
            transactionTypeMenuExpanded = !transactionTypeMenuExpanded
        }) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = selectedType.name,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Transaction Type") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = transactionTypeMenuExpanded
                    )
                },
            )
            ExposedDropdownMenu(expanded = transactionTypeMenuExpanded,
                onDismissRequest = { transactionTypeMenuExpanded = false }) {
                values().forEach { transactionType ->
                    DropdownMenuItem(onClick = {
                        selectedType = transactionType
                        transactionTypeMenuExpanded = false
                    }) {
                        Text(text = transactionType.name)
                    }
                }
            }
        }

        Text(text = "Debtor:")

        AddDebtor(group, selectedType) { payType: PayType ->
            debtors = payType
        }

        OutlinedButton(onClick = {
            val transaction = Transaction(
                transactionName.text,
                transactionNameCost.text.toFloat(),
                list,
                selectedType,
                debtors!!
            )
            val transaction2: Type = when (selectedType) {
                EQUAL -> {
                    TypeEqual(
                        Transaction2(
                        transactionName.text,
                        transactionNameCost.text.toFloat(),
                        list,
                        // FIXME:
                    )
                    )
                }
                PERCENT -> {
                    TypePercent(
                        Transaction2(
                        transactionName.text,
                        transactionNameCost.text.toFloat(),
                        list,
                        // FIXME:
                    ), mapOf() // FIXME: add ui
                    )
                }
                else -> {
                    throw Exception("")
                }
            }
            onClick.invoke(transaction2)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Create Transaction")
        }
    }
}

@Composable
private fun GoTextField(
    modifier: Modifier = Modifier,
    labelText: String = "",
    placeholderText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    value: TextFieldState = remember { TextFieldState("") },
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value.text,
        onValueChange = { value.text = it },
        label = { Text(text = labelText) },
        placeholder = { Text(text = placeholderText) },
        readOnly = readOnly,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Preview(showBackground = true)
@Composable
fun GoTextFieldPreview() {
    GoTextField(
        labelText = "label",
        placeholderText = "place holder",
        keyboardType = KeyboardType.Text,
    )
}

class TextFieldState(value: String = "") {
    var text: String by mutableStateOf(value)
}

@Composable
private fun MemberItem(debtor: Debtor, onClick: (Boolean) -> Unit) {
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = debtor.name, modifier = Modifier
                .padding(start = 8.dp)
                .weight(4f)
        )

        Checkbox(modifier = Modifier.weight(1f), checked = checkedState.value, onCheckedChange = {
            checkedState.value = it
            onClick.invoke(it)
        })
    }

}

@Composable
private fun ListItem(debtor: Debtor, Float: Float) {
    Card(
        modifier = Modifier.fillMaxWidth(), elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = debtor.name,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "+ $Float",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun AddPayer(group: Group, onClick: (Debtor, Float) -> Unit) {
    var groupMemberExpanded by remember { mutableStateOf(false) }

    var memberSelected by remember { mutableStateOf(Debtor("")) }
    var memberPayedCost by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        ExposedDropdownMenuBox(expanded = groupMemberExpanded, onExpandedChange = {
            groupMemberExpanded = !groupMemberExpanded
        }) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = memberSelected.name,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Group Member") },
                placeholder = { Text(text = "Select how payed.") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = groupMemberExpanded
                    )
                },
            )
            ExposedDropdownMenu(expanded = groupMemberExpanded,
                onDismissRequest = { groupMemberExpanded = false }) {
                group.members.forEach { member ->
                    DropdownMenuItem(onClick = {
                        memberSelected = member
                        groupMemberExpanded = false

                    }) {
                        Text(text = member.name)
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(4f),
                value = memberPayedCost,
                onValueChange = { value -> memberPayedCost = value },
                label = { Text(text = "Cost") },
                placeholder = { Text(text = "How many did the member pay?") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            IconButton(modifier = Modifier.weight(1f), onClick = {
                if (memberSelected.name.isNotEmpty() && memberPayedCost.text.isNotEmpty()) {
                    onClick.invoke(memberSelected, memberPayedCost.text.toFloat())
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Composable
private fun AddDebtor(group: Group, type: TransactionType, onClick: (PayType) -> Unit) {
    when (type) {
        EQUAL -> {
            val whoDebt = PayEqual(arrayListOf())

            group.members.forEach { debtor ->
                MemberItem(debtor) {
                    if (it) {
                        if (!whoDebt.payers.contains(debtor)) {
                            whoDebt.payers.add(debtor)
                        }
                    } else {
                        if (whoDebt.payers.contains(debtor)) {
                            whoDebt.payers.remove(debtor)
                        }
                    }
                }
            }
            Button(onClick = { onClick.invoke(whoDebt) }) {
                Text(text = "Add")
            }
        }
        FAMILY -> {
        }
        PERCENT -> {
        }
        FIX -> {
        }
    }
}