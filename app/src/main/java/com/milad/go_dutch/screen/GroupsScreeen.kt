package com.milad.go_dutch.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.milad.core.data.Group
import com.milad.go_dutch.HomeFloatingActionButton
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.data.groupA
import com.milad.go_dutch.isScrollingUp

@Composable
fun HomeScreen(navController: NavHostController, groupList: SnapshotStateList<Group>) {
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = { MyTopAppBar("Groups") },
        floatingActionButton = {
            HomeFloatingActionButton(
                lazyListState.isScrollingUp(),
                "Create Group",
                Icons.Default.Add
            ) {
                navController.navigate("createGroup")
            }
        },
    ) { padding ->
        MainList(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth(),
            navController = navController,
            listState = lazyListState,
            groupList = groupList
        )
    }
}

@Composable
private fun MainList(
    modifier: Modifier,
    navController: NavHostController,
    listState: LazyListState,
    groupList: SnapshotStateList<Group>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        state = listState,
        modifier = modifier
    ) {
        itemsIndexed(groupList) { index, item ->
            ListItem(item) {
                navController.navigate("transactions/${index}")
            }
        }
    }
}

@Composable
private fun ListItem(group: Group, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        elevation = 4.dp
    ) {
        Row(
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = group.name,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Column {
                Text(
                    text = "${group.members.size} Member",
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${group.transactions.size} Transaction",
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun ListItemPreview() {
    ListItem(group = groupA, onClick = {})
}