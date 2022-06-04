package com.milad.go_dutch

import com.milad.go_dutch.data.Debtor

fun MutableMap<Debtor, Double>.sort(): MutableMap<Debtor, Double> {
    return entries
        .sortedBy { it.value }
        .reversed()
        .associate { it.toPair() }
        .toMutableMap()

}
fun MutableMap<Debtor, Double>.reverseSort(): MutableMap<Debtor, Double> {
    return entries
        .sortedBy { it.value }
        .associate { it.toPair() }
        .toMutableMap()

}

fun MutableMap<Debtor, Double>.collocationPlusForEach(
    totalUserCost: MutableMap<Debtor, Double>
) = forEach {
    if (totalUserCost.containsKey(it.key)) {
        totalUserCost[it.key] = totalUserCost.getValue(it.key) + it.value
    } else
        totalUserCost[it.key] = it.value
}
