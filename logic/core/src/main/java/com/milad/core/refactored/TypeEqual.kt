package com.milad.core.refactored

import com.milad.core.data.Debtor

class TypeEqual(val transaction: Transaction2) : Type {
    val debtorList = mutableMapOf<Debtor, Float>()

    override fun calculate(): TypeEqual {

        val dividedCost = transaction.transactionCost / transaction.debtors.size

        // who pay in transaction
        transaction.debtors.forEach {
            if (debtorList.contains(it)) {
                debtorList[it] = debtorList.getValue(it) + dividedCost
            } else
                debtorList[it] = dividedCost
        }

        // (who pay in transaction - dividedCost)
        transaction.payers.forEach {
            if (debtorList.containsKey(it.key)) {
                debtorList[it.key] = debtorList.getValue(it.key) - it.value
            } else {
                debtorList[it.key] = -it.value
            }
        }

        return this
    }
}