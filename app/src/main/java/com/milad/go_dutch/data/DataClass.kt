package com.milad.go_dutch.data

import com.milad.go_dutch.PayType


data class Debtor(val name: String)
enum class TransactionType {
    EQUAL, FAMILY, PERCENT, FIX
}

data class Transaction(
    val name: String,
    val cost: Double,
    val payers: Map<Debtor, Double>,
    val transactionType: TransactionType,
    val payType: PayType
)

data class TransactionInfo(
    val transaction: Transaction,
    val payersInTransaction: MutableMap<Debtor, Double>,
    val usersCostInTransaction: MutableMap<Debtor, Double>
)