package arma.ark.cartograph

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("arma.ark.cartograph")
@EnableAutoConfiguration
open class AppController {
    companion object {
        @Throws(Exception::class)
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(AppController::class.java, *args)
        }
    }
}