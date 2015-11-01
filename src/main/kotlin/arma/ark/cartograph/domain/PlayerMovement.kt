package arma.ark.cartograph.domain

import org.joda.time.DateTime

data class PlayerMovement(
    val id: Int,
    val created: DateTime,
    val createdIngame: Float,
    val playerId: Int,
    val position: Position,
    val vehicle: String?
)