package arma.ark.cartograph.util.position

import arma.ark.cartograph.domain.Position

fun parsePosition(value: String): Position {
    if (value.length < 2 || value[0] != '[' || value[value.length - 1] != ']') return Position(0F, 0F, 0F)
    val floats = value.substring(1..value.length - 2).split(',')
    if (floats.size != 3 ) return Position(0F, 0F, 0F)
    return try {
        Position(floats[0].toFloat(), floats[1].toFloat(), floats[2].toFloat())
    } catch (e: NumberFormatException) {
        Position(0F, 0F, 0F)
    }
}