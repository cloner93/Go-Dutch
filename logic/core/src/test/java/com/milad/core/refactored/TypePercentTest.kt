package com.milad.core.refactored

import com.milad.core.data.Debtor
import org.junit.Assert.*
import org.junit.Test

class TypePercentTest {
    private lateinit var type: TypePercent

    private val testTransaction1 = Transaction2(
        "test",
        180_000F,
        mapOf(Debtor("milad") to 180_000F),
        /*listOf(
            Debtor("milad"),
            Debtor("mahdi"),
            Debtor("masoud"),
        )*/
    )

    var mapPercent = mutableMapOf(
        Debtor("A") to 10,
        Debtor("B") to 40,
        Debtor("C") to 50
    )

    @Test
    fun `check calculated`() {
        type = TypePercent(testTransaction1, mapPercent)
//        type.calculate()

        assertNotNull(type.debtorList)
    }

    @Test
    fun `check null safety`() {
        type = TypePercent(testTransaction1, mapPercent)
        type.calculate()

        assertNotNull(type.debtorList)
    }


    @Test
    fun `check debtorList value`() {

        type = TypePercent(testTransaction1, mapPercent)
        val result = type.calculate()
        var sum= 0f
        result.debtorList.forEach { sum+=it.value }

        assertEquals(sum , testTransaction1.transactionCost)
    }

}