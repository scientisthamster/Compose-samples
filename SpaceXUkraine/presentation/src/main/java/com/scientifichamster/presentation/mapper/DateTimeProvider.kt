package com.scientifichamster.presentation.mapper

import org.joda.time.DateTime
import javax.inject.Inject

class DateTimeProvider @Inject constructor() {
    fun today(): DateTime = DateTime.now()
}