package arma.ark.cartograph.config

import arma.ark.cartograph.rest.serialize.JodaDateTimeJsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import javax.sql.DataSource

@Component
open class AppConfig {
    @Bean
    @Primary
    public open fun objectMapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder()
                .serializerByType(LocalDateTime::class.java, JodaDateTimeJsonSerializer())
                .build()
    }

    @Bean
    public open fun webMvcConfigurerAdapter(@Value("\${map.path}") mapPath: String): WebMvcConfigurerAdapter {
        return object : WebMvcConfigurerAdapter() {
            override fun addViewControllers(registry: ViewControllerRegistry) {
                registry.addViewController("/").setViewName("forward:/index.html");
                registry.addViewController("/replay/**").setViewName("forward:/replay.html");
            }

            override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
                registry
                    .addResourceHandler("/images/map/**")
                    .addResourceLocations("file:${mapPath}");
            }
        };
    }
}