package com.milad.go_dutch

import com.milad.go_dutch.data.*
import com.milad.go_dutch.data.TransactionType

import org.junit.Test

class GoDutchTest {

    private val allUser = listOf(
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
    private val payTypeAllUser: PayType = PayEqual(allUser)
    private val payTypeSpecificUser: PayType = PayEqual(
        listOf(
            Debtor("hamid ahmadreza"),
            Debtor("hamid safar")
        )
    )

    private val gheluon = Transaction(
        "Gheluon",
        180000.0,
        mapOf(Debtor("hamed safar") to 180_000.0),
        TransactionType.EQUAL,
        payTypeAllUser
    )
    private val hamom = Transaction(
        "Hamom",
        175_000.0,
        mapOf(
            Debtor("milad") to 10_000.0,
            Debtor("masoud") to 10_000.0,
            Debtor("hojat daee") to 10_000.0,
            Debtor("hamed safar") to 45_000.0,
            Debtor("abolfazl") to 50_000.0,
            Debtor("hamid safar") to 50_000.0
        ),
        TransactionType.EQUAL,
        payTypeAllUser
    )
    private val test = Transaction(
        "test",
        1_000_000.0,
        mapOf(
            Debtor("milad") to 1_000_000.0
        ),
        TransactionType.EQUAL,
        payTypeSpecificUser
    )
    private lateinit var goDutch: GoDutch

    @Test
    fun initGoDutch() {
        goDutch = GoDutch(listOf(hamom,gheluon,test))

        goDutch.calculate()
//        assertNotNull(goDutch)
//        assertEquals(listOf(gheluon, hamom), goDutch.transactions)
//        assertEquals(6, goDutch.payers.size)
//        assertEquals(225000L, goDutch.payers.get(Debtor("hamed safar")))
//        assertEquals(355000L, goDutch.totalCost)
    }

}