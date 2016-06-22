package arma.ark.cartograph.mission.data

import arma.ark.cartograph.mission.date.DateTime
import arma.ark.cartograph.mission.date.Duration
import arma.ark.cartograph.mission.date.Session
import arma.ark.cartograph.mission.weather.Weather
import arma.ark.cartograph.mission.world.World
import org.joda.time.LocalDateTime

enum class MissionAttributeId(val id: Int) {
    NAME(1), WORLD(2), DATE(3), TIME(4), FOG(5), WEATHER(6)
}

enum class MisisonType {
    COOP, COTVT, TVT, GTVT, UNKOWN
}

data class Mission(
    val id: Int,
    val created: LocalDateTime,
    val type: MisisonType,
    val maxPlayers: Int,
    val name: String,
    val world: World,
    val dateTime: DateTime,
    val duration: Duration,
    val session: Session,
    val weather: Weather,
    val actualPlayers: Int
)