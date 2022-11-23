package com.milad.go_dutch.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import com.milad.core.data.*
import kotlinx.coroutines.flow.MutableStateFlow

val allUser = arrayListOf(
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
val payers = mapOf(
    Debtor("milad") to 5_000.0,
    Debtor("masoud") to 7_000.0,
)
val list: ArrayList<Transaction> = arrayListOf(
    Transaction(
        "Last night dinner", 13000.0, payers, TransactionType.EQUAL, PayEqual(
            arrayListOf(
                Debtor("mahdi")
            )
        )
    ),
    Transaction("weekend fast food", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
    Transaction("train ticket", 13000.0, payers, TransactionType.EQUAL, PayEqual(allUser)),
    Transaction(
        "the cost of repairing the house",
        13000.0,
        payers,
        TransactionType.EQUAL,
        PayEqual(allUser)
    ),
)


var groupA: Group = Group("Group A", allUser, list)


var groupList = mutableStateListOf<Group>(groupA)
var transactionList = mutableStateListOf<Transaction>()

