package com.milad.go_dutch.data

import androidx.compose.runtime.mutableStateListOf
import com.milad.core.data.*
import com.milad.core.type.Type

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

val payers = mapOf(
    Debtor("milad") to 5_000.0,
    Debtor("masoud") to 7_000.0,
)
val list: ArrayList<Type> = arrayListOf()

var groupA: Group = Group("Group A", allUser, list)

var groupList = mutableStateListOf(groupA)

var calculationsResult = mutableListOf<Type>()

