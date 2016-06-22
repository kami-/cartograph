package arma.ark.cartograph.mission.weather

import arma.ark.cartograph.util.arma.parseFloatSqfArray

val WEATHER_DESCRIPTIONS = arrayOf(
    Pair(0f, "Clear"),
    Pair(0.48f, "Overcast"),
    Pair(1f, "Storm")
)

data class Weather(
    val description: String,
    val overcast: Float,
    val rain: Float,
    val rainbow: Float,
    val lightnings: Float,
    val windStrength: Float,
    val windGusts: Float,
    val waves: Float,
    val humidity: Float,
    val fogStrength: Float,
    val fogDecay: Float,
    val fogBase: Float
)

fun getWeather(fogStr: String?, weatherStr: String?): Weather {
    val fog = parseFloatSqfArray(fogStr)
    val weather = parseFloatSqfArray(weatherStr)
    val finalFog = if (fog.size != 3) FloatArray(3){0f} else fog
    val finalWeather = if (weather.size != 8) FloatArray(8){0f} else weather
    return Weather(getWeatherDescription(finalWeather[0]), finalFog[0], finalFog[1], finalFog[2], finalWeather[0], finalWeather[1], finalWeather[2], finalWeather[3], finalWeather[4], finalWeather[5], finalWeather[6], finalWeather[7])
}

private fun getWeatherDescription(overcast: Float): String {
    val default = "Clear"
    if (WEATHER_DESCRIPTIONS.isEmpty()) return default
    val descs = WEATHER_DESCRIPTIONS.plus(Pair(1f, default))
    for (idx in (0..WEATHER_DESCRIPTIONS.size - 1)) {
        val currentDesc = descs[idx]
        val nextDesc = descs[idx + 1]
        if (overcast >= currentDesc.first && overcast < nextDesc.first) return currentDesc.second
    }
    return default
}