package com.milad.core2

import org.junit.After
import org.junit.Before
import org.junit.Test

class EqualPurchaseTest {
    private lateinit var equalPurchase: EqualPurchase

    @Before
    fun setUp() {
        val buyers = mapOf<Person,Long>(Person("Milad") to 500,Person("Mohammad") to 500)
        val consumers = listOf<Person>(Person("Milad"),Person("Mohammad"))
        equalPurchase = EqualPurchase("k", 1000,buyers,consumers)
    }

    @After
    fun tearDown() {

    }

    @Test(expected = IllegalArgumentException::class)
    fun `EqualPurchase class with empty name should throw exception`() {
        equalPurchase.name = ""
    }

    @Test(expected = IllegalArgumentException::class)
    fun `purchase class negative amount should throw exception`() {
        equalPurchase.amount = 0
    }

    @Test(expected = IllegalArgumentException::class)
    fun `EqualPurchase class should have non empty buyers throw exception`() {
        equalPurchase.buyers = mapOf()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `EqualPurchase class should have non empty consumers`() {
        equalPurchase.consumers = listOf()
    }
}