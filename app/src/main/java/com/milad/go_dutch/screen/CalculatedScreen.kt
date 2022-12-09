package com.milad.go_dutch.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.milad.core.GoDutch
import com.milad.core.data.TransactionInfo
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.data.calculationsResult
import com.milad.go_dutch.data.list
import com.milad.go_dutch.data.payers

@Composable
fun CalculatedScreen(navController: NavHostController, index: String) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = { MyTopAppBar("Calculated Transactions") }) { paddingValues ->
        CalculatedList(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxHeight()
                .fillMaxWidth(),
            listState = lazyListState,
            list = calculationsResult,
        )

    }
}

@Composable
fun CalculatedList(modifier: Modifier, listState: LazyListState, list: List<TransactionInfo>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        state = listState,
        modifier = modifier
    ) {
        items(list) {
            ListItem(it)
        }
    }
}

@Preview
@Composable
fun PreviewListItem() {
    val data = GoDutch(list).calculateEachMember()
    ListItem(transactionInfo = data.first())
}

@Composable
private fun ListItem(transactionInfo: TransactionInfo) {
    var expended by remember { mutableStateOf(true) }
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = transactionInfo.transaction.name)
                Text(text = transactionInfo.transaction.cost.toString(), color = Color.Black)
            }
            AnimatedVisibility(expended) {
                Column() {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Creditors:")
                    for (item in transactionInfo.payersInTransaction) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "   * " + item.key.name)
                            Text(text = item.value.toString()+"   " , color = Color.Green)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Debtors:")
                    for (item in transactionInfo.usersCostInTransaction) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "   * "+item.key.name)
                            Text(text = item.value.toString()+"   " , color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}