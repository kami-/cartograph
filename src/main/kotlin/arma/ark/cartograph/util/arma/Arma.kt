package arma.ark.cartograph.util.arma

fun parseIntSqfArray(strArray: String?): IntArray {
    if (strArray == null || strArray.length < 2 || strArray.first() != '[' || strArray.last() != ']') { return intArrayOf() }
    val splitArray =  strArray.substring(1, strArray.length - 2).split(',')
    return splitArray.map { try { it.toInt() } catch (nfe: NumberFormatException) { 0 } }.toIntArray()
}

fun parseFloatSqfArray(strArray: String?): FloatArray {
    if (strArray == null || strArray.length < 2 || strArray.first() != '[' || strArray.last() != ']') { return floatArrayOf() }
    val splitArray =  strArray.substring(1, strArray.length - 2).split(',')
    return splitArray.map { try { it.toFloat() } catch (nfe: NumberFormatException) { 0f } }.toFloatArray()
}