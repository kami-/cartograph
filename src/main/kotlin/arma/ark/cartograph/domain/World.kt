package arma.ark.cartograph.domain

data class World(
    val name: String,
    val width: Int,
    val height: Int,
    val minZoom: Int,
    val maxZoom: Int
)