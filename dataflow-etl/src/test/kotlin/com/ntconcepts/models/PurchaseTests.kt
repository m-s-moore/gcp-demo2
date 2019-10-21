package com.ntconcepts.models

import com.ntconcepts.gcpdemo2.models.Purchase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PurchaseTests {

    @Test
    fun getTableRow() {
        val user = Purchase(123)
        val tableRow = Purchase.Outputs.toTableRow(user)
        val uid = tableRow["User_ID"]

        assertEquals(user.User_ID, uid)

    }
}