package com.milad.go_dutch.data

import androidx.compose.runtime.mutableStateListOf
import com.milad.core.data.*

val allUser = arrayListOf(
    Debtor("milad"),
    Debtor("mahdi"),
    Debtor("masoud"),
    Debtor("abolfazl"),
    Debtor("hojat daee"),
    Debtor("hamed nabi"),
    Debtor("hamed safar"),
    Debtor("hamid ahmadreza"),
    Debtor("hamid safar"),
)

val payTypeAllUser: PayType = PayEqual(allUser)

val playStation = Transaction(
    "playStation",
    180000.0,
    mapOf(Debtor("hamed safar") to 180_000.0),
    TransactionType.EQUAL,
    payTypeAllUser
)
val dinner = Transaction(
    "dinner", 180_000.0, mapOf(
        Debtor("milad") to 15_000.0,
        Debtor("masoud") to 10_000.0,
        Debtor("hojat daee") to 10_000.0,
        Debtor("hamed safar") to 45_000.0,
        Debtor("abolfazl") to 50_000.0,
        Debtor("hamid safar") to 50_000.0
    ), TransactionType.EQUAL, payTypeAllUser
)

val payers = mapOf(
    Debtor("milad") to 5_000.0,
    Debtor("masoud") to 7_000.0,
)
val list: ArrayList<Transaction> = arrayListOf(
    dinner, playStation
)


var groupA: Group = Group("Group A", allUser, list)


var groupList = mutableStateListOf<Group>(groupA)
var transactionList = mutableStateListOf<Transaction>()

var calculationsResult = mutableListOf<TransactionInfo>()

