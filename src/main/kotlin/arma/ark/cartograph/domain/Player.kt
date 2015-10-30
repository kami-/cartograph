package arma.ark.cartograph.domain

import org.joda.time.DateTime

data class Player(
    val id: Int,
    val created: DateTime,
    val createdIngame: Int,
    val missionId: Int,
    val uid: String,
    val name: String,
    val hullGearClass: String?,
    val groupName: String,
    val isJip: Boolean,
    val death: DateTime?,
    val deathIngame: Int?
)