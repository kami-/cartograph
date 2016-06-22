package arma.ark.cartograph.mission.date

import arma.ark.cartograph.config.DATE_FORMAT
import arma.ark.cartograph.util.arma.parseIntSqfArray
import org.joda.time.DateTimeConstants
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

const val SESSION_END_HOUR = 6;
val DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT)

data class DateTimeDescription(
    val hour: Int,
    val minute: Int,
    val description: String
)

val DATE_TIME_DESCRIPTIONS = arrayOf(
    DateTimeDescription(4, 50, "Dawn"),
    DateTimeDescription(5, 50, "Early Morning"),
    DateTimeDescription(9, 0, "Morning"),
    DateTimeDescription(12, 0, "Noon"),
    DateTimeDescription(15, 0, "Afternoon"),
    DateTimeDescription(17, 50, "Evening"),
    DateTimeDescription(18, 50, "Dusk"),
    DateTimeDescription(0, 0, "Night")
)

data class Session(
    val week: Int,
    val day: String
)

data class DateTime(
    val description: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val hour: Int,
    val minute: Int
)

data class Duration(
    val hour: Int,
    val minute: Int,
    val second: Int
)

fun parseNullDate(dateStr: String?): LocalDateTime {
    return if (dateStr != null)
        DATE_FORMATTER.parseLocalDateTime(dateStr)
    else
        LocalDateTime.now()
}

fun getSession(created: LocalDateTime): Session {
    val day = when {
        created.dayOfWeek == DateTimeConstants.SATURDAY || created.dayOfWeek == DateTimeConstants.SUNDAY && created.hourOfDay <= SESSION_END_HOUR -> "Saturday"
        created.dayOfWeek == DateTimeConstants.SUNDAY || created.dayOfWeek == DateTimeConstants.MONDAY && created.hourOfDay <= SESSION_END_HOUR -> "Sunday"
        else -> "Unknown"
    }
    return Session(created.weekOfWeekyear, day)
}

fun getDateTime(dateStr: String?, timeStr: String?): DateTime {
    val date = parseIntSqfArray(dateStr)
    val time = parseIntSqfArray(timeStr)
    val finalDate = if (date.size != 3) IntArray(3){0} else date
    val finalTime = if (time.size != 2) IntArray(2){0} else time
    return DateTime(getDateTimeDescription(finalTime[0], finalTime[1]), finalDate[0], finalDate[1], finalDate[2], finalTime[0], finalTime[1])
}

fun getDuration(duration: Float?): Duration {
    val finalDuration = (duration ?: 0f).toInt()
    return Duration(finalDuration / 3600, finalDuration % 3600 / 60, finalDuration % 60)
}

fun getDateTimeDescription(hour: Int, minute: Int): String {
    if (DATE_TIME_DESCRIPTIONS.isEmpty()) return "Noon"
    val time = hour * 60 + minute;
    val dtds = DATE_TIME_DESCRIPTIONS.plus(DATE_TIME_DESCRIPTIONS[0])
    for (idx in (0..DATE_TIME_DESCRIPTIONS.size - 1)) {
        val dtd = dtds[idx]
        val nextDtd = dtds[idx + 1]
        if (time in (dtd.hour * 60 + dtd.minute .. nextDtd.hour * 60 + nextDtd.minute - 1)) return dtd.description
    }
    return "Noon"
}