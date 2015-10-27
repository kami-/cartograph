package arma.ark.cartograph.domain

data class Mission(
    val id: Int,
    val created: String,
    val name: String,
    val worldName: String,
    val safetyTimer: String?,
    val safetyTimerIngame: Int?,
    val end: String?,
    val endIngame: Int?
)