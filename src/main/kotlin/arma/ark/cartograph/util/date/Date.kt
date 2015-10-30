package arma.ark.cartograph.util.date

import arma.ark.cartograph.config.DATE_FORMAT
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

val DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT)

fun parseNullDate(dateStr: String?): DateTime? {
    return if (dateStr != null)
        DATE_FORMATTER.parseDateTime(dateStr)
    else
        null
}