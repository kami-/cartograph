package arma.ark.cartograph.domain

import org.joda.time.DateTime

data class AiMovement(
    val id: Int,
    val created: DateTime,
    val createdIngame: Float,
    val missionId: Int,
    val position: Position,
    val groupName: String,
    val aliveCount: Int,
    val vehicle: String?,
    val waypointPosition: Position,
    val waypointType: String
)