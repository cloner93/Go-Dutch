package com.milad.core2

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class EqualPurchaseTest {
    lateinit var equalPurchase: EqualPurchase
    @Before
    fun setUp() {
        equalPurchase = EqualPurchase()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `EqualPurchase class should have non empty name`(){
        assertTrue(equalPurchase.name.isNotEmpty())
    }

    @Test
    fun `purchase class should positive amount`(){
        assertTrue(equalPurchase.amount > 0)
    }
}