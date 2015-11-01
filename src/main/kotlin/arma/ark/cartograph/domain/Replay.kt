package arma.ark.cartograph.domain

data class Replay(
    val mission: Mission,
    val players: List<Player>,
    val playerMovements: Map<Int, List<PlayerMovement>>,
    val aiMovements: Map<Int, List<AiMovement>>
)