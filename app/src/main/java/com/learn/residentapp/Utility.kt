package com.learn.residentapp

import androidx.databinding.InverseMethod
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    const val ADD: String = "ADD"
    const val EDIT: String = "EDIT"
    const val DATA: String = "DATA"

    var df: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    @JvmStatic fun stringToInt(value: String): Int {
        if(value.isNotEmpty()) {
            return Integer.parseInt(value)
        }
        return 0
    }

    @InverseMethod("stringToInt")
    @JvmStatic fun intToString(value: Int): String {
        return ""+value
    }


    @JvmStatic fun stringToDate(value: String): Date {
        return df.parse(value)
    }

    @InverseMethod("stringToDate")
    @JvmStatic fun dateToString(value: Date): String {
        return df.format(value)
    }

    fun calculateAge(dob : Calendar) : Int {
        val today = Calendar.getInstance()

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age
    }
}