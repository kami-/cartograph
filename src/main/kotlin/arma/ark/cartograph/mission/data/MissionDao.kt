package arma.ark.cartograph.mission.data

import arma.ark.cartograph.config.DB_DATE_FORMAT
import arma.ark.cartograph.entity.EntityAttributeId
import arma.ark.cartograph.mission.date.DATE_FORMATTER
import arma.ark.cartograph.mission.date.getDateTime
import arma.ark.cartograph.mission.date.getDuration
import arma.ark.cartograph.mission.date.getSession
import arma.ark.cartograph.mission.weather.getWeather
import arma.ark.cartograph.mission.world.DEFAULT_WORLD
import arma.ark.cartograph.mission.world.WORLDS
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class MissionDao @Autowired constructor(val jdbcTemplate: JdbcTemplate) {
    private val LOGGER = LoggerFactory.getLogger(javaClass)

    fun getAll(): List<Mission> {
        val sql = """
            SELECT m.id
                , DATE_FORMAT(m.created, '$DB_DATE_FORMAT') AS created
                , name.char_value AS name
                , world.char_value AS world
                , date.char_value AS date
                , time.char_value AS time
                , (SELECT MAX(ee.gameTime) FROM entity_event ee WHERE ee.mission_id = m.id) AS duration
                , fog.char_value AS fog
                , weather.char_value AS weather
                , (SELECT COUNT(DISTINCT ea.char_value) FROM entity_attribute ea WHERE ea.mission_id = m.id AND ea.attribute_type_id = ?) AS actualPlayers
            FROM mission m
            LEFT JOIN mission_attribute name ON m.id = name.mission_id AND name.attribute_type_id = ?
            LEFT JOIN mission_attribute world ON m.id = world.mission_id AND world.attribute_type_id = ?
            LEFT JOIN mission_attribute date ON m.id = date.mission_id AND date.attribute_type_id = ?
            LEFT JOIN mission_attribute time ON m.id = time.mission_id AND time.attribute_type_id = ?
            LEFT JOIN mission_attribute fog ON m.id = fog.mission_id AND fog.attribute_type_id = ?
            LEFT JOIN mission_attribute weather ON m.id = weather.mission_id AND weather.attribute_type_id = ?
            ORDER BY m.created DESC;
        """
        val params = arrayOf(EntityAttributeId.PLAYER_NAME.id).plus(MissionAttributeId.values().map { it.id }.toTypedArray())
        return jdbcTemplate.query(sql, params) { rs, rn -> mapMission(rs) }
    }

    private fun mapMission(resultSet: ResultSet): Mission {
        val name = resultSet.getString("name") ?: ""
        val created = DATE_FORMATTER.parseLocalDateTime(resultSet.getString("created"))
        return Mission(
            resultSet.getInt("id"),
            created,
            getMissionType(name),
            getMaxPlayers(name),
            getName(name),
            WORLDS.getOrElse(resultSet.getString("world").toLowerCase(), { DEFAULT_WORLD }),
            getDateTime(resultSet.getString("date"), resultSet.getString("time")),
            getDuration(resultSet.getFloat("duration")),
            getSession(created),
            getWeather(resultSet.getString("fog"), resultSet.getString("weather")),
            resultSet.getInt("actualPlayers")
        )
    }

    private fun getMissionType(name: String): MisisonType {
        return when {
            name.startsWith("ark_co") -> MisisonType.COOP
            name.startsWith("ark_tvt") -> MisisonType.TVT
            name.startsWith("ark_cotvt") -> MisisonType.COTVT
            name.startsWith("ark_gtvt") -> MisisonType.GTVT
            else -> MisisonType.UNKOWN
        }
    }

    private fun getMaxPlayers(name: String): Int {
        val splitName = name.split('_')
        if (splitName.size < 3) return 0
        return try { splitName[1].dropWhile { !it.isDigit() }.toInt() } catch (nfe: NumberFormatException) { 0 }
    }

    private fun getName(name: String): String {
        val splitName = name.split('_')
        if (splitName.size < 3) return ""
        return splitName.filterIndexed { i, s -> i > 1 }.joinToString("_")
    }
}