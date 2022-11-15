package com.milad.core

import com.milad.core.data.Debtor
import com.milad.core.data.Transaction
import com.milad.core.data.TransactionInfo
import com.milad.core.data.TransactionType
import kotlin.math.roundToLong

class GoDutch(transactions: List<Transaction>) {
    private var totalAmount = TotalAmount()

    init {
        transactions.forEach { transaction ->
            val payersInTransaction = mutableMapOf<Debtor, Double>()
            val usersCostInTransaction = mutableMapOf<Debtor, Double>()

            transaction.payers.forEach { payer ->
                payersInTransaction[payer.key] = payer.value
            }

            when (transaction.transactionType) {
                TransactionType.EQUAL -> {
                    val debtorsCount = transaction.payType.payers.size
                    val dividedCost = transaction.cost / debtorsCount
                    transaction.payType.payers.forEach {
                        if (usersCostInTransaction.containsKey(it)) {
                            usersCostInTransaction[it] =
                                usersCostInTransaction.getValue(it) + dividedCost
                        } else
                            usersCostInTransaction[it] = dividedCost
                    }
                    payersInTransaction.forEach {
                        if (usersCostInTransaction.containsKey(it.key)) {
                            usersCostInTransaction[it.key] =
                                usersCostInTransaction.getValue(it.key) - it.value
                        } else {
                            usersCostInTransaction[it.key] = -it.value
                        }
                    }
                }
                else -> {}
            }

            totalAmount.transactionInfoList.add(
                TransactionInfo(
                    transaction,
                    payersInTransaction,
                    usersCostInTransaction
                )
            )
        }
    }

    fun calculateEachMember(): MutableList<TransactionInfo> {
        totalAmount.transactionInfoList.forEach { transactionInfo ->
            println(transactionInfo.transaction.name)
            val creditors = mutableMapOf<Debtor, Double>()
            val debtors = mutableMapOf<Debtor, Double>()
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
    }
}

class TotalAmount {
    val transactionInfoList = mutableListOf<TransactionInfo>()
    val totalTransactionInfoList = mutableListOf<TransactionInfo>()

    fun sumOneByOne(){
        // TODO
    }
}