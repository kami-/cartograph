
package arma.ark.cartograph.rest.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ReplayNotFoundException constructor(message: String) : RuntimeException(message) {
}