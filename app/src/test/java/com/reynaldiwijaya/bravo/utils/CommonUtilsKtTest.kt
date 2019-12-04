package com.reynaldiwijaya.bravo.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class CommonUtilsKtTest {

    @Test
    fun changeFormatDate() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", changeFormatDate(date))
    }
}