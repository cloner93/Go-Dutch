package com.milad.go_dutch

import com.milad.go_dutch.data.*

fun main(args: Array<String>) {
}


interface PayType {
    val payers: List<Debtor>
}

class PayEqual(override val payers: List<Debtor>) : PayType {}
class PayFamily(override val payers: List<Debtor>) : PayType {}
class PayPercent(override val payers: List<Debtor>) : PayType {}
class PayFix(override val payers: List<Debtor>) : PayType {}

