package com.milad.core.refactored

import com.milad.core.data.*

interface Type {
    fun calculate(): Type
}

data class Transaction2(
    val name: String,
    val transactionCost: Float,
    var payers: Map<Debtor, Float> = mapOf(),
    var debtors: List<Debtor> = listOf()
)

data class TransactionInfo2(
    val transaction: Transaction2,
    val usersCostInTransaction: MutableMap<Debtor, Float> = mutableMapOf()
)