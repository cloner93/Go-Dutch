package com.milad.core.refactored

import com.milad.core.data.Debtor

class TypePercent(val transaction: Transaction2, private val percentMap: Map<Debtor, Int>) : Type {
    var debtorList: MutableMap<Debtor, Float> = mutableMapOf()

    override fun calculate(): TypePercent {
        val cost = transaction.transactionCost

        var checkSum = 0

        percentMap.forEach {
            checkSum += it.value
        }
        if (checkSum != 100)
            throw Exception("Invalid percent array.")

        debtorList = percentMap
            .map {
                it.key to it.value * cost / 100
            }
            .toMap()
            .toMutableMap()

        return this
    }
}