package com.milad.core.data

data class Debtor(val name: String)
enum class TransactionType {
    EQUAL, FAMILY, PERCENT, FIX
}
data class Group(
    val name: String,
    val transactions:List<Transaction>
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
    val payers: List<Debtor>
}

class PayEqual(override val payers: List<Debtor>) : PayType
class PayFamily(override val payers: List<Debtor>) : PayType
class PayPercent(override val payers: List<Debtor>) : PayType
class PayFix(override val payers: List<Debtor>) : PayType
