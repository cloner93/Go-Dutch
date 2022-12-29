package com.milad.core.data


fun MutableMap<Debtor, Float>.sort(): MutableMap<Debtor, Float> {
    return entries
        .sortedBy { it.value }
        .reversed()
        .associate { it.toPair() }
        .toMutableMap()

}
fun MutableMap<Debtor, Float>.reverseSort(): MutableMap<Debtor, Float> {
    return entries
        .sortedBy { it.value }
        .associate { it.toPair() }
        .toMutableMap()

}

fun MutableMap<Debtor, Float>.collocationPlusForEach(
    totalUserCost: MutableMap<Debtor, Float>
) = forEach {
    if (totalUserCost.containsKey(it.key)) {
        totalUserCost[it.key] = totalUserCost.getValue(it.key) + it.value
    } else
        totalUserCost[it.key] = it.value
}
