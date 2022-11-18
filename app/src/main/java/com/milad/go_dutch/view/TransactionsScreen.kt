package com.milad.go_dutch.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.milad.core.data.*
import com.milad.go_dutch.HomeFloatingActionButton
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.isScrollingUp

@Composable
fun TransactionsScreen() {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = { MyTopAppBar("Create Transaction") },
        floatingActionButton = {
            HomeFloatingActionButton(
                lazyListState.isScrollingUp(),
                "Calculate",
                Icons.Default.Done
            ) {}
        },
        content = TransactionsScreenContent(lazyListState)
    )
}

@Composable
fun TransactionsScreenContent(
    lazyListState: LazyListState
): @Composable (PaddingValues) -> Unit =
    {
        val payers = mapOf(
            Debtor("milad") to 5_000.0,
            Debtor("masoud") to 7_000.0,
        )
        val allUser = listOf(
            Debtor("milad"),
            Debtor("mahdi"),
            Debtor("masoud")
        )
        val list: List<Transaction> = listOf(
            Transaction("Last night dinner", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
            Transaction("weekend fast food", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
            Transaction("train ticket", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
            Transaction("the cost of repairing the house", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            state = lazyListState,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            items(list) {
                ListItem(it)
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Text(text = "Add Transaction")
                }
            }
        }
    }

@Composable
private fun ListItem(transaction: Transaction) {
    var expended by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            Modifier
                .clickable { expended = !expended }
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = transaction.name)
                Text(text = transaction.cost.toString())
            }
            AnimatedVisibility(expended) {
                Column() {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Payers:")
                    transaction.payers.forEach(){ (payer , cost) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(text = payer.name)
                            Text(text = cost.toString())
                        }
                    }

                    Text("Debtors:")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "All Member")
                        Text(text = transaction.transactionType.name)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier
                                .height(18.dp)
                                .width(18.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            modifier = Modifier
                                .height(18.dp)
                                .width(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TransactionsScreenPreview() {
    TransactionsScreen()
}