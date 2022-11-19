package com.milad.go_dutch.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.milad.go_dutch.MyTopAppBar
import com.milad.go_dutch.data.groupList


val groupMemberList  = mutableStateListOf<Debtor>()

@Composable
fun CreateGroupScreen(navController: NavHostController) {
    val lazyListState = rememberLazyListState()

    Scaffold(topBar = { MyTopAppBar("Create Group") }) { padding ->
        List(
            navController,
            groupMemberList,
            lazyListState = lazyListState,
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth()
        )
    }
}

@Composable
private fun List(
    navController: NavHostController,
    listOfMember: SnapshotStateList<Debtor>,
    lazyListState: LazyListState,
    modifier: Modifier
) {
    var groupName by remember { mutableStateOf(TextFieldValue("")) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        state = lazyListState,
        modifier = modifier
    ) {
        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = groupName,
                onValueChange = { value -> groupName = value },
                label = { Text(text = "Group name") },
                placeholder = { Text(text = "Enter your group name.") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
        items(listOfMember) {
            listItem(it)
        }
        item {
            AddMember()
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedButton(onClick = {
                    groupMemberList.clear()
                    navController.popBackStack()
                }) {
                    Text("Cancel")
                }
                Button(onClick = {
                    if (groupName.text.isNotEmpty() && groupMemberList.size!= 0){
                        val group = Group(groupName.text, groupMemberList.toList(), listOf())
                        groupList.add(group)
                        groupMemberList.clear()
                        navController.popBackStack()
                    }
                }) {
                    Text("Create")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddMemberPreview() {
    AddMember()
}

@Composable
private fun AddMember() {

    var memberName by remember { mutableStateOf(TextFieldValue("")) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = memberName,
            onValueChange = { value -> memberName = value },
            label = { Text(text = "Member name") },
            placeholder = { Text(text = "Enter member name.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        IconButton(onClick = {
            if (memberName.text.isNotEmpty())
                groupMemberList.add(Debtor(memberName.text))
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
private fun listItem(it: Debtor) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(Modifier.padding(8.dp)) {
            Text(
                text = it.name,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}