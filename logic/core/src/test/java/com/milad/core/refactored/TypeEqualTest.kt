package com.milad.core.refactored

import com.milad.core.data.Debtor
import org.junit.Assert.*

import org.junit.Test

class TypeEqualTest {
    private lateinit var type: TypeEqual

    private val payer = mapOf(
        Debtor("milad") to 180_000F,
    )
    private val debtor = listOf(
        Debtor("milad"),
        Debtor("mahdi"),
        Debtor("masoud"),
    )
    private val transaction = Transaction2("test", 180_000F, payer, debtor)

    @Test
    fun `check null safety`() {

        type = TypeEqual(transaction)
        val result = type.calculate()

        assertNotNull(result)
    }

    @Test
    fun `check payer`() {

        type = TypeEqual(transaction)
        val result = type.calculate()

        assertEquals(result.payersInTransaction.size, 1)
        assertEquals(result.payersInTransaction[Debtor("milad")], 180_000F)
    }

    @Test
    fun `check debtor`() {

        type = TypeEqual(transaction)
        val result = type.calculate()

        assertEquals(result.usersCostInTransaction.size, 3)
        assertEquals(result.usersCostInTransaction[Debtor("milad")], -120_000F)
    }
}