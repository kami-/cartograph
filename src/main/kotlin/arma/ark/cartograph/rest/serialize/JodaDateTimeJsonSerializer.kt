package arma.ark.cartograph.rest.serialize

import arma.ark.cartograph.util.date.DATE_FORMATTER
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.DateTime

class JodaDateTimeJsonSerializer : JsonSerializer<DateTime>() {
    override fun serialize(dateTime: DateTime?, generator: JsonGenerator?, provider: SerializerProvider?) {
        val json = if (dateTime != null)
            DATE_FORMATTER.print(dateTime)
        else
            "null"
        generator?.writeString(json)
    }
}