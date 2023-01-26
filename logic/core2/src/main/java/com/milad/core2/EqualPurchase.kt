package com.milad.core2

class EqualPurchase(_name: String, _amount: Long) : Purchase() {

    override var name: String = _name
        set(value) {
            if (value.isEmpty())
                throw IllegalArgumentException("purchase name should not be empty!")

            field = value
        }

    override var amount: Long = _amount
        set(value) {
            if (value <= 0)
                throw IllegalArgumentException("purchase amount should be grater than zero!")

            field = value
        }

    override var buyers: List<Person> = listOf()
    override var consumers: List<Person> = listOf()

    override fun calculate(): Purchase {
        TODO("Not yet implemented")
    }
}