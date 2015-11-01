package arma.ark.cartograph.domain

import org.joda.time.DateTime

data class Mission(
    val id: Int,
    val created: DateTime,
    val name: String,
    val worldName: String,
    val safetyTimer: DateTime?,
    val safetyTimerIngame: Float?,
    val end: DateTime?,
    val endIngame: Float?
)