package com.milad.core

import com.milad.core.type.Type
import com.milad.core.type.TypeEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

//@RunWith(MockitoTestRule)
class TransactionImplTest {
    @Mock
    lateinit var type: Type

    // TODO:
    /*@Mock
    lateinit var database: Database*/

    private lateinit var transaction: Transaction

//    private val result = TransactionInfo2(Transaction2("", 0F))

    @Before
    fun before() {
        type = Mockito.mock(TypeEqual::class.java)
        transaction = TransactionImpl(type, "0")
    }

    @Test
    fun `first test for implementation`() {
//        Mockito.`when`(type.calculate()).thenReturn(result)

//        assertEquals(transaction.add())
    }

}