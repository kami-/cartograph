package arma.ark.cartograph.config

import arma.ark.cartograph.rest.serialize.JodaDateTimeJsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import org.joda.time.DateTime
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.stereotype.Component

@Component
open class AppConfig {
    @Bean
    @Primary
    public open fun objectMapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder()
                .serializerByType(DateTime::class.java, JodaDateTimeJsonSerializer())
                .build()
    }
}