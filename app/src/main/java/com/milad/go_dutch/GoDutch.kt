package com.milad.go_dutch

import com.milad.go_dutch.data.Debtor
import com.milad.go_dutch.data.Transaction
import com.milad.go_dutch.data.TransactionInfo
import com.milad.go_dutch.data.TransactionType

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

//            totalAmount.totalCost += transaction.cost
//            payersInTransaction.collocationPlusForEach(totalAmount.totalPayers)
//            usersCostInTransaction.collocationPlusForEach(totalAmount.totalUserCost)

            totalAmount.addTransactionInfo(transaction, payersInTransaction, usersCostInTransaction)
        }
    }

    fun calculate() {
        //  test
        totalAmount.totalTransactionInfoList.forEach { transactionInfo ->
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
            val creditorsSorted = creditors.sort()
            val debtorsSorted = debtors.sort()

//            println(sumCreditor)
//            println(sumDebtors)
            println(-sumCreditor == sumDebtors)
        }
        //  gheluon
        //  hamom
    }
}

class TotalAmount {
    //    var totalCost: Double = 0L
//    val totalPayers = mutableMapOf<Debtor, Double>()
//    val totalUserCost = mutableMapOf<Debtor, Double>()
    val totalTransactionInfoList = mutableListOf<TransactionInfo>()

    fun addTransactionInfo(
        transaction: Transaction,
        payersInTransaction: MutableMap<Debtor, Double>,
        usersCostInTransaction: MutableMap<Debtor, Double>
    ) = totalTransactionInfoList.add(
        TransactionInfo(
            transaction,
            payersInTransaction,
            usersCostInTransaction
        )
    )
}