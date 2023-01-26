package com.milad.core2

abstract class Purchase {
    abstract var name: String
    abstract var amount: Long
    abstract var buyers: Map<Person,Long>
    abstract var consumers: List<Person>

    abstract fun calculate(): Purchase
}