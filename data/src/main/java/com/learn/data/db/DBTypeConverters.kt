package com.learn.data.db

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DBTypeConverters {

    private var df: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value == null) null else df.parse(value)
    }

    @TypeConverter
    fun dateToTimestamp(value: Date?): String? {
        return if (value == null) null else df.format(value)
    }

}