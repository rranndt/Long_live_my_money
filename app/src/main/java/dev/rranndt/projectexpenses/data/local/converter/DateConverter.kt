package dev.rranndt.projectexpenses.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId

class DateConverter {

    @TypeConverter
    fun toDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun toDateLong(date: LocalDate?): Long? {
        val zoneId = ZoneId.systemDefault()
        return date?.atStartOfDay(zoneId)?.toEpochSecond()
    }
}