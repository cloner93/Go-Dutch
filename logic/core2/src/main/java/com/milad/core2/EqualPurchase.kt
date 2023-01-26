package com.milad.core2

class EqualPurchase(_name: String, _amount: Long,_buyers:Map<Person,Long>,_consumers:List<Person>) : Purchase() {

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

    override var buyers: Map<Person,Long> = _buyers
        set(value) {
            if (value.isEmpty())
                throw IllegalArgumentException("every purchase should have at least one buyer!")

            field = value
        }
    override var consumers: List<Person> = _consumers
        set(value) {
            if (value.isEmpty())
                throw IllegalArgumentException("every purchase should have at least one consumer!")

            field = value
        }

    override fun calculate(): Purchase {
        TODO("Not yet implemented")
    }
}