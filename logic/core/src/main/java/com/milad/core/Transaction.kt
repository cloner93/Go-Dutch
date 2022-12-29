package com.milad.core

import android.util.Log
import com.milad.core.type.Type

interface Transaction {
    val type: Type

    fun add()
    fun edit()
    fun delete()
}

class TransactionImpl(
    override val type: Type,
    private val groupId: String
) : Transaction {

    override fun add() {
        val result = type.calculate()

        Log.d("TAG", "add")

//        database.list[groupId] = result
    }

    override fun edit() {
        val result = type.calculate()

        Log.d("TAG", "edit")
//        if (database.list.contains())
//        database.list[groupId] = result
    }

    override fun delete() {
        Log.d("TAG", "delete")
    }
}