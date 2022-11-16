package com.milad.go_dutch.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TransactionsScreen() {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = { TopAppBar("Create Transaction") },
        floatingActionButton = {
            HomeFloatingActionButton(
                lazyListState.isScrollingUp(),
                "Create Transaction"
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
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            state = lazyListState,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            item {
                listItem()
            }
        }
    }

@Composable
private fun listItem() {
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
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "Last night diner")
                Text(text = "13.000")
            }
            AnimatedVisibility(expended) {
                Column() {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Payers:")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Milad Targholi")
                        Text(text = "6.000")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Milad Targholi")
                        Text(text = "7.000")
                    }
                    Text("Debtors:")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "All Member")
                        Text(text = "EQUAL")
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