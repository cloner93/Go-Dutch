package com.milad.go_dutch.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
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
import com.milad.core.data.Debtor
import com.milad.core.data.Group
import com.milad.core.data.PayEqual
import com.milad.core.data.TransactionType
import com.milad.core.data.TransactionType.*
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.data.groupA
import com.milad.go_dutch.data.groupList
import java.lang.reflect.Member

@Composable
fun CreateTransactionScreen(navController: NavHostController, index: String) {
    val lazyListState = rememberLazyListState()
    val group = groupList[index.toInt()]

    Scaffold(topBar = { MyTopAppBar("Create Transaction") }) { padding ->
        createTransactionList(
            group = group,
            transactionList,
            lazyListState = lazyListState,
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth(),
        )
    }
}

var transactionList = mutableStateMapOf<Debtor, Double>()

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun createTransactionList(
    group: Group,
    list: SnapshotStateMap<Debtor, Double>,
    lazyListState: LazyListState,
    modifier: Modifier
) {
    var transactionName by remember { mutableStateOf(TextFieldValue("")) }
    var transactionNameCost by remember { mutableStateOf(TextFieldValue("")) }

    var transactionTypeMenuExpanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(EQUAL) }


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
                expanded = transactionTypeMenuExpanded,
                onExpandedChange = {
                    transactionTypeMenuExpanded = !transactionTypeMenuExpanded
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
                            expanded = transactionTypeMenuExpanded
                        )
                    },
                )
                ExposedDropdownMenu(
                    expanded = transactionTypeMenuExpanded,
                    onDismissRequest = { transactionTypeMenuExpanded = false }
                ) {
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

            Text(text = "Payers:")
        }

        items(list.toList()) { (debtor, double) ->
            listItem(debtor, double)
        }

        item {
            AddPayer(group) { debtor, cost ->
                transactionList[debtor] = cost
            }
        }
        item {
            addDebtor(group, selectedType)
        }
    }
}

@Composable
private fun addDebtor(group: Group, selectedType: TransactionType) {

    Text(text = "Debtor:")
    when (selectedType) {
        EQUAL -> {
            val howDebt = PayEqual(arrayListOf()) // TODO: add this to fake data

            group.members.forEach { debtor ->
                MemberItem(debtor) {
                    if (it) {
                        if (!howDebt.payers.contains(debtor)){
                            howDebt.payers.add(debtor)
                        }
                    }else{
                        if (howDebt.payers.contains(debtor)){
                            howDebt.payers.remove(debtor)
                        }
                    }
                }
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

@Composable
fun MemberItem(debtor: Debtor, onClick: (Boolean) -> Unit) {
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = debtor.name, modifier = Modifier
                .padding(start = 8.dp)
                .weight(4f)
        )

        Checkbox(
            modifier = Modifier.weight(1f),
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onClick.invoke(it)
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun addDebtorPreview() {
    MemberItem(debtor = Debtor("Milad Targholi")) {}
}


@Composable
fun listItem(debtor: Debtor, double: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
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
                text = "+ $double",
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

@Preview
@Composable
fun itemPrev() {
    listItem(Debtor("Milad Targholi"), 200000.0)
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun AddPayer(
    group: Group,
    onClick: (Debtor, Double) -> Unit
) {
    var groupMemberExpanded by remember { mutableStateOf(false) }

    var memberSelected by remember { mutableStateOf(Debtor("")) }
    var memberPayedCost by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        ExposedDropdownMenuBox(
            expanded = groupMemberExpanded,
            onExpandedChange = {
                groupMemberExpanded = !groupMemberExpanded
            }
        ) {
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
            ExposedDropdownMenu(
                expanded = groupMemberExpanded,
                onDismissRequest = { groupMemberExpanded = false }
            ) {
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
                    onClick.invoke(memberSelected, memberPayedCost.text.toDouble())
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun addMemberPreview() {
    AddPayer(group = groupA) { _, _ ->
//        transactionList[debtor] = cost
    }
}