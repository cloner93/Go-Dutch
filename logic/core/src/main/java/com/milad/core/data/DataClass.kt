package com.milad.core.data

import com.milad.core.type.Type

data class Debtor(val name: String)
enum class TransactionType {
    EQUAL, FAMILY, PERCENT, FIX
}
data class Group(
    val name: String,
    val members: ArrayList<Debtor>,
    val transactions:ArrayList<Type>
)

data class Transaction(
    val name: String,
    val cost: Float,
    val payers: Map<Debtor, Float>,
    val transactionType: TransactionType,
    val payType: PayType
)

data class TransactionInfo(
    val transaction: Transaction,
    val payersInTransaction: MutableMap<Debtor, Float>,
    val usersCostInTransaction: MutableMap<Debtor, Float>
)


interface PayType {
    val payers: ArrayList<Debtor>
}

class PayEqual(override val payers: ArrayList<Debtor>) : PayType
class PayFamily(override val payers: ArrayList<Debtor>) : PayType
class PayPercent(override val payers: ArrayList<Debtor>) : PayType
class PayFix(override val payers: ArrayList<Debtor>) : PayType
