package com.milad.core

import com.milad.core.data.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

import org.junit.Test
import kotlin.math.roundToLong

class GoDutchTest {
    private lateinit var goDutch: GoDutch

    @Test
    fun test_case_1() {
        val allUser = listOf(
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
        val payTypeAllUser: PayType = PayEqual(allUser)

        val gheluon = com.milad.core.data.Transaction(
            "Gheluon",
            180000.0,
            mapOf(Debtor("hamed safar") to 180_000.0),
            TransactionType.EQUAL,
            payTypeAllUser
        )
        val hamom = Transaction(
            "Hamom", 180_000.0, mapOf(
                Debtor("milad") to 15_000.0,
                Debtor("masoud") to 10_000.0,
                Debtor("hojat daee") to 10_000.0,
                Debtor("hamed safar") to 45_000.0,
                Debtor("abolfazl") to 50_000.0,
                Debtor("hamid safar") to 50_000.0
            ), TransactionType.EQUAL, payTypeAllUser
        )

        goDutch = GoDutch(listOf(hamom, gheluon))
        val list = goDutch.calculateEachMember()

        assertNotNull(list)
        assertEquals("transaction size", list.size, 2)
        assertEquals("transaction second", list[1].transaction, gheluon)
        assertEquals("", list[0].payersInTransaction.size, 3)
        assertEquals("", list[0].usersCostInTransaction[Debtor("mahdi")], 20000.0)

        assertEquals("", list[1].payersInTransaction.size, 1)
        assertEquals("", list[1].payersInTransaction[Debtor("hamed safar")], -160000.0)

    }

    @Test
    fun test_case_2() {
        val allUser = listOf(
            Debtor("milad"),
            Debtor("mohammad"),
            Debtor("ehsan")
        )
        val payTypeAllUser: PayType = PayEqual(allUser)

        val kabab = com.milad.core.data.Transaction(
            "kabab",
            160_000.0,
            mapOf(Debtor("ehsan") to 160_000.0),
            TransactionType.EQUAL,
            payTypeAllUser
        )

        goDutch = GoDutch(listOf(kabab))
        val list = goDutch.calculateEachMember()

        assertNotNull(list)
        assertEquals(list.size, 1)

        assertEquals(list[0].transaction, kabab)
        assertEquals(list[0].payersInTransaction.size, 1)
        assertEquals(list[0].payersInTransaction[Debtor("ehsan")], -106666.66666666666)

    }

    @Test
    fun test_case_3() {
        val allUser = listOf(
            Debtor("ehsan"),
            Debtor("samanhe"),
            Debtor("fatemhe"),
            Debtor("milad"),
            Debtor("rasol"),
            Debtor("mahan"),
            Debtor("amin"),
            Debtor("niusha"),
            Debtor("sina"),
        )
        val payTypeAllUser: PayType = PayEqual(allUser)

        val SibZamini = com.milad.core.data.Transaction(
            "SibZamini",
            400_000.0,
            mapOf(Debtor("amin") to 400_000.0),
            TransactionType.EQUAL,
            payTypeAllUser
        )
        val Masala = com.milad.core.data.Transaction(
            "Masala",
            200_000.0,
            mapOf(Debtor("milad") to 200_000.0),
            TransactionType.EQUAL,
            payTypeAllUser
        )
        val Sham = com.milad.core.data.Transaction(
            "Sham",
            1_400_000.0,
            mapOf(Debtor("mahan") to 1_400_000.0),
            TransactionType.EQUAL,
            payTypeAllUser
        )

        goDutch = GoDutch(listOf(SibZamini, Masala, Sham))
        val list = goDutch.calculateEachMember()

        assertNotNull(list)
        assertEquals(list.size, 3)

        assertEquals(list[0].transaction, SibZamini)
        assertEquals(list[0].payersInTransaction.size, 1)
        assertEquals(
            list[0].payersInTransaction[Debtor("amin")]!!.roundToLong(),
            (-355555.5555555556).roundToLong()
        )

    }

}