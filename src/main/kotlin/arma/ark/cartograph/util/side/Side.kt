package arma.ark.cartograph.util.side

val DEFAULT_SIDE = "blufor"

fun groupNameToSide(groupName: String): String {
    if (groupName.isEmpty()) return DEFAULT_SIDE;
    return when (groupName[0]) {
        'B' -> "blufor"
        'O' -> "opfor"
        'R' -> "indfor"
        'C' -> "civilian"
        else -> DEFAULT_SIDE
    }
}