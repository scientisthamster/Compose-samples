package com.scientifichamster.presentation.mapper

import org.joda.time.DateTime
import org.joda.time.Days
import javax.inject.Inject
import kotlin.math.absoluteValue

interface DateTransformer {
    fun dateToDateString(dateTime: DateTime): String
    fun getDifferanceOfDays(dateTime: DateTime): String
    fun isPast(launchDate: DateTime): Boolean
}

class DateTransformerImpl @Inject constructor(
    private val dateTimeProvider: DateTimeProvider
) : DateTransformer {

    override fun dateToDateString(dateTime: DateTime): String {
        val date =
            { "${dateTime.dayOfMonth.getStringValue()}-${dateTime.monthOfYear.getStringValue()}-${dateTime.yearOfEra.getStringValue()}" }
        val time =
            { "${dateTime.hourOfDay.getStringValue()}:${dateTime.minuteOfHour.getStringValue()}" }
        return "$date at $time"
    }

    override fun getDifferanceOfDays(dateTime: DateTime): String {
        val dayDifferance = Days.daysBetween(dateTimeProvider.today(), dateTime)
        return dayDifferance.days.absoluteValue.toString()
    }

    override fun isPast(launchDate: DateTime) = launchDate.isBefore(dateTimeProvider.today())

    private fun Int.getStringValue() = getTwoDigitsValue()

    private fun Int.getTwoDigitsValue() =
        if (this < 10) "0$this" else this.toString()
}