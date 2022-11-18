package com.milad.go_dutch.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import com.milad.core.data.TransactionInfo
import com.milad.go_dutch.MyTopAppBar

@Composable
fun CalculatedScreen() {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = { MyTopAppBar("Calculated Transactions") },
        content = calculatedScreenContent(lazyListState)
    )
}

@Preview
@Composable
fun CalculatedScreenPreview() {
    CalculatedScreen()
}

@Composable
fun calculatedScreenContent(
    lazyListState: LazyListState
): @Composable (PaddingValues) -> Unit =
    {
        val itemList = listOf<TransactionInfo>()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            state = lazyListState,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            items(itemList) {
                ListItem(it)
            }
        }
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
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "Milad targholi")
                Column {
                    Text(text = "+ 123.000", color = Color.Green)
                    Text(text = "- 1.000", color = Color.Red)
                }
            }
            AnimatedVisibility(expended) {
                Column() {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Transactions:")
                    repeat(3) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(text = "payer.name")
                            Text(text = "cost.toString()")
                        }
                    }
                }
            }
        }
    }
}