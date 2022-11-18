package com.milad.go_dutch.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.milad.core.data.Group
import com.milad.go_dutch.HomeFloatingActionButton
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.data.groupA
import com.milad.go_dutch.isScrollingUp

@Composable
fun HomeScreen(groupList: SnapshotStateList<Group>) {
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = { MyTopAppBar("Groups") },
        floatingActionButton = {
            HomeFloatingActionButton(
                lazyListState.isScrollingUp(),
                "Create Group",
                Icons.Default.Add
            ) {
                groupList.add(groupA)
            }
        },
    ) { padding ->
        MainList(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth(),
            listState = lazyListState,
            groupList = groupList
        )
    }
}

@Composable
private fun MainList(
    modifier: Modifier,
    listState: LazyListState,
    groupList: SnapshotStateList<Group>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        state = listState,
        modifier = modifier
    ) {
        items(groupList) {
            ListItem(it)
        }
    }
}

@Composable
private fun ListItem(group: Group) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(Modifier.padding(8.dp)) {
            Text(
                text = group.name,
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
fun ListItemPreview() {
    ListItem(group = groupA)
}