package com.milad.go_dutch.view

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val lazyListState = rememberLazyListState()
    val groupList = listOf(
        "group a", "group b", "group c", "group d", "group e", "group f"
    )
    Scaffold(
        topBar = { TopAppBar("Groups") },
        floatingActionButton = {
            HomeFloatingActionButton(
                lazyListState.isScrollingUp(),
                "Create Group",
                Icons.Default.Add
            ) {}
        },
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            state = lazyListState,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            items(groupList) {
                listItem(it)
            }

        }
    }

@Composable
private fun listItem(it: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(Modifier.padding(8.dp)) {
            Text(
                text = it.repeat(10),
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun listItemPreview() {
    listItem("Milad Targholi")
}

@Composable
fun LazyListState.isScrollingUp(): Boolean {
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
fun HomeFloatingActionButton(
    extended: Boolean,
    text: String = "",
    icon: ImageVector,
    onClick: () -> Unit,
) {
    FloatingActionButton(onClick = onClick) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            AnimatedVisibility(visible = extended) {
                Text(
                    text = text,
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
    HomeFloatingActionButton(false, "Create Group", Icons.Default.Add) {}
}

@Preview
@Composable
fun collapsedPreview() {
    HomeFloatingActionButton(true, "Create Group", Icons.Default.Add) {}
}

@Composable
fun TopAppBar(title: String) {
    TopAppBar(title = { Text(title) })
}