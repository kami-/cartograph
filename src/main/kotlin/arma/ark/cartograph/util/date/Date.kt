package arma.ark.cartograph.util.date

import arma.ark.cartograph.config.DATE_FORMAT
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.Interval
import org.joda.time.format.DateTimeFormat

// Session should start at 18:00
val SESSION_START_HOUR = 18
// Session should finnish before next day 04:00
val SESSION_END_HOUR = 4
val DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT)

fun parseNullDate(dateStr: String?): DateTime? {
    return if (dateStr != null)
        DATE_FORMATTER.parseDateTime(dateStr)
    else
        null
}

fun isDuringSession(dateTime: DateTime): Boolean {
    val sessionStart = dateTime.withDayOfWeek(DateTimeConstants.SATURDAY).withTime(SESSION_START_HOUR, 0, 0, 0)
    val sessionEnd = dateTime.withDayOfWeek(DateTimeConstants.SUNDAY).withTime(SESSION_END_HOUR, 0, 0, 0)
    val sessionInterval = Interval(sessionStart, sessionEnd)
    return sessionInterval.contains(dateTime)
}