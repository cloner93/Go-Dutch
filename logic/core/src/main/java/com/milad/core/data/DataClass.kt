package com.milad.core.data

import java.io.Serializable

data class Debtor(val name: String)
enum class TransactionType {
    EQUAL, FAMILY, PERCENT, FIX
}
data class Group(
    val name: String,
    val members: ArrayList<Debtor>,
    val transactions:ArrayList<Transaction>
)

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


interface PayType {
    val payers: ArrayList<Debtor>
}

class PayEqual(override val payers: ArrayList<Debtor>) : PayType
class PayFamily(override val payers: ArrayList<Debtor>) : PayType
class PayPercent(override val payers: ArrayList<Debtor>) : PayType
class PayFix(override val payers: ArrayList<Debtor>) : PayType
