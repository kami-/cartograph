package arma.ark.cartograph.domain

import org.joda.time.DateTime

data class SimpleMission(
    val id: Int,
    val created: DateTime,
    val name: String,
    val worldName: String
)