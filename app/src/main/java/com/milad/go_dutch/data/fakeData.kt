package com.milad.go_dutch.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import com.milad.core.data.*
import kotlinx.coroutines.flow.MutableStateFlow

val allUser = listOf(
    Debtor("milad"),
    Debtor("mahdi"),
    Debtor("masoud"),
)
val payTypeAllUser: PayType = PayEqual(allUser)

val transactionA = Transaction(
    "Hamom", 25_000.0, mapOf(
        Debtor("milad") to 15_000.0,
        Debtor("masoud") to 10_000.0,
    ), TransactionType.EQUAL, payTypeAllUser
)

var groupA: Group = Group("Group A", allUser, listOf(transactionA))


var groupList = mutableStateListOf<Group>()
var transactionList = mutableStateListOf<Transaction>()

val payers = mapOf(
    Debtor("milad") to 5_000.0,
    Debtor("masoud") to 7_000.0,
)
val list: List<Transaction> = listOf(
    Transaction("Last night dinner", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
    Transaction("weekend fast food", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
    Transaction("train ticket", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
    Transaction("the cost of repairing the house", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
)
