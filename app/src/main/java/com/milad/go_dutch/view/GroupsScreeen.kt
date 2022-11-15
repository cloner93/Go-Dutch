package com.milad.go_dutch.view

import android.annotation.SuppressLint
import android.preference.PreferenceActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val lazyListState = rememberLazyListState()
    val groupList = listOf("group a", "group b", "group c", "group d", "group e", "group f")
    Scaffold(
        topBar = { TopAppBar() },
        floatingActionButton = { HomeFloatingActionButton(lazyListState.isScrollingUp()) {} },
        content = HomeScreenContent(lazyListState, groupList)
    )
}

@Composable
private fun HomeScreenContent(
    lazyListState: LazyListState,
    groupList: List<String>
): @Composable (PaddingValues) -> Unit =
    {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            state = lazyListState,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            items(groupList) {
                Card() {

                }
                Text(text = it)
            }

        }
    }

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
private fun HomeFloatingActionButton(
    extended: Boolean,
    onClick: () -> Unit
) {
    FloatingActionButton(onClick = onClick) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            AnimatedVisibility(visible = extended) {
                Text(
                    text = "Create Group",
                    modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun groupsScreenPreview() {
    HomeScreen()
}

@Preview
@Composable
fun expendedPreview() {
    HomeFloatingActionButton(false) {}
}

@Preview
@Composable
fun collapsedPreview() {
    HomeFloatingActionButton(true) {}
}

@Composable
fun TopAppBar() {
    TopAppBar(title = { Text("Groups") })
}