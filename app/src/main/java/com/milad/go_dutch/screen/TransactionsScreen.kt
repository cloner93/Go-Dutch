package com.milad.go_dutch.screen

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.milad.core.type.Type
import com.milad.go_dutch.HomeFloatingActionButton
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.data.calculationsResult
import com.milad.go_dutch.data.groupList
import com.milad.go_dutch.isScrollingUp

@Composable
fun TransactionsScreen(navController: NavHostController, index: String) {
    val lazyListState = rememberLazyListState()
    val groupTransactionList: List<Type> = groupList[index.toInt()].transactions

    Scaffold(
        topBar = { MyTopAppBar("Create Transaction") },
        floatingActionButton = {
            HomeFloatingActionButton(
                lazyListState.isScrollingUp(),
                "Calculate",
                Icons.Default.Done
            ) {

                groupTransactionList.forEach {
                    calculationsResult.add(it.calculate())
                }
                navController.navigate("calculated/${index}")
            }
        }
    ) { padding ->
        TransactionList(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth(),
            navController = navController,
            lazyListState = lazyListState,
            list = groupTransactionList,
            groupIndex = index
        )
    }
}

@Composable
fun TransactionList(
    modifier: Modifier,
    navController: NavHostController,
    list: List<Type>,
    lazyListState: LazyListState,
    groupIndex: String
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        state = lazyListState,
        modifier = modifier
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
                    .clickable {
                        navController.navigate("createTransaction/${groupIndex}")
                    }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(text = "Add Transaction")
            }
        }
    }
}

@Composable
private fun ListItem(type: Type) {
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = type.transaction.name)
                Text(text = type.transaction.transactionCost.toString())
            }
            AnimatedVisibility(expended) {
                Column() {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Payers:")
                    type.transaction.payers.forEach() { (payer, cost) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "   * " + payer.name)
                            Text(text = "$cost   ")
                        }
                    }

                    Text("Debtors:")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "   All Member")
                        Text(text = "$type  ")
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