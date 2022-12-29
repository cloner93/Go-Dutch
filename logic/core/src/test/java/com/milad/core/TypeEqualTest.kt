package com.milad.core

import com.milad.core.data.Debtor
import com.milad.core.type.Transaction2
import com.milad.core.type.TypeEqual
import org.junit.Assert.*

import org.junit.Test

class TypeEqualTest {
    private lateinit var type: TypeEqual

    private val testTransaction1 = Transaction2(
        "test",
        180_000F,
        mapOf(Debtor("milad") to 180_000F),
        listOf(
            Debtor("milad"),
            Debtor("mahdi"),
            Debtor("masoud"),
        )
    )

    private val testTransaction2 = Transaction2(
        "test2",
        180_000f,
        mapOf(
            Debtor("milad") to 15_000f,
            Debtor("masoud") to 10_000f,
            Debtor("hojat daee") to 10_000f,
            Debtor("hamed safar") to 45_000f,
            Debtor("abolfazl") to 50_000f,
            Debtor("hamid safar") to 50_000f
        ),
        listOf(
            Debtor("milad"),
            Debtor("mahdi"),
            Debtor("masoud"),
            Debtor("abolfazl"),
            Debtor("hojat daee"),
            Debtor("hamed nabi"),
            Debtor("hamed safar"),
            Debtor("hamid ahmadreza"),
            Debtor("hamid safar"),
        )
    )

    @Test
    fun `check null safety`() {

        type = TypeEqual(testTransaction1)
        val result = type.calculate()

        assertNotNull(result)
    }

    @Test
    fun `check debtor`() {

        type = TypeEqual(testTransaction1)
        val result = type.calculate()

        assertEquals(result.debtorList.size, 3)
        assertEquals(result.debtorList[Debtor("milad")], -120_000F)
    }

    @Test
    fun `check sum of debt`() {

        type = TypeEqual(testTransaction2)
        val result = type.calculate()
        var sumDebt = 0f
        result.debtorList.forEach {
            sumDebt += it.value
        }

        assertEquals(sumDebt, 0F)
    }
}