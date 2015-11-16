package arma.ark.cartograph.domain

data class Replay(
    val mission: Mission,
    val players: List<Player>,
    val playerMovements: List<PlayerMovement>,
    val aiMovements: List<AiMovement>
)