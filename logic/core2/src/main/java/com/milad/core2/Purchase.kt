package com.milad.core2

abstract class Purchase {
    abstract val name : String
    abstract val amount : Long
    abstract val buyers : List<Person>
    abstract val consumers : List<Person>

    abstract fun calculate() : Purchase
}

class EqualPurchase() : Purchase() {
    override val name: String
        get() = TODO("Not yet implemented")
    override val amount: Long
        get() = TODO("Not yet implemented")
    override val buyers: List<Person>
        get() = TODO("Not yet implemented")
    override val consumers: List<Person>
        get() = TODO("Not yet implemented")

    override fun calculate(): Purchase {
        TODO("Not yet implemented")
    }
}

class PercentagePurchase() : Purchase() {
    override val name: String
        get() = TODO("Not yet implemented")
    override val amount: Long
        get() = TODO("Not yet implemented")
    override val buyers: List<Person>
        get() = TODO("Not yet implemented")
    override val consumers: List<Person>
        get() = TODO("Not yet implemented")

    override fun calculate(): Purchase {
        TODO("Not yet implemented")
    }
}