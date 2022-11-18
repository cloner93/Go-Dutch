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
import com.milad.go_dutch.MyTopAppBar

@Composable
fun CreateGroupScreen() {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = { MyTopAppBar("Create Group") },
        content = CreateGroupContent(lazyListState)
    )
}

@Preview
@Composable
fun createGroupsPreview() {
    CreateGroupScreen()
}

@Composable
private fun CreateGroupContent(
    lazyListState: LazyListState
): @Composable (PaddingValues) -> Unit =
    {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        val listOfMember = listOf<String>(
            "milad targholi",
            "milad targholi",
            "milad targholi",
            "milad targholi",
            "milad targholi",
            "milad targholi"
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            state = lazyListState,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            item {
                OutlinedTextField(
                    value = text,
                    onValueChange = { value -> text = value },
                    label = { Text(text = "Group name") },
                    placeholder = { Text(text = "Enter your group name.") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }
            items(listOfMember) {
                listItem(it)
            }
            item {
                Row(

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Text(text = "Add Member")
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text("Cancel")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text("Create")
                    }
                }
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