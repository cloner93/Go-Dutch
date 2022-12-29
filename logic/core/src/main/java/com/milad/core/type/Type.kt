package com.milad.core.type

import com.milad.core.data.*

abstract class Type(open val transaction: Transaction2) {
    abstract fun calculate(): Type
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

/*    fun calculateEachMember(): MutableList<TransactionInfo> {
        totalAmount.transactionInfoList.forEach { transactionInfo ->
            println(transactionInfo.transaction.name)
            val creditors = mutableMapOf<Debtor, Float>()
            val debtors = mutableMapOf<Debtor, Float>()
            var sumCreditor = 0.0
            var sumDebtors = 0.0

            transactionInfo.usersCostInTransaction.forEach {
                if (it.value < 0) {
                    creditors[it.key] = it.value
                    sumCreditor += it.value
                } else {
                    debtors[it.key] = it.value
                    sumDebtors += it.value
                }
            }

            if ((-sumCreditor).roundToLong() != sumDebtors.roundToLong())
                throw Exception("we have wrong calculation. please check again.")

            totalAmount.totalTransactionInfoList.add(
                TransactionInfo(
                    transactionInfo.transaction,
                    creditors,
                    debtors
                )
            )
        }

        return totalAmount.totalTransactionInfoList
    }*/