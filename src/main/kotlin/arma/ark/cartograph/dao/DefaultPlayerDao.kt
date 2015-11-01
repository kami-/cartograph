package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.Player
import arma.ark.cartograph.util.date.DATE_FORMATTER
import arma.ark.cartograph.util.date.parseNullDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DefaultPlayerDao @Autowired constructor(val jdbcTemplate: JdbcTemplate) : PlayerDao {
    override fun getAllByMissionId(missionId: Int): List<Player> {
        val sql = "SELECT * FROM player WHERE mission_id = ?"
        return jdbcTemplate.query(sql, arrayOf(missionId), { rs, rn -> mapPlayer(rs) })
    }

    private fun mapPlayer(resultSet: ResultSet): Player {
        return Player(
            resultSet.getInt("id"),
            DATE_FORMATTER.parseDateTime(resultSet.getString("created")),
            resultSet.getFloat("created_ingame"),
            resultSet.getInt("mission_id"),
            resultSet.getString("player_uid"),
            resultSet.getString("player_name"),
            resultSet.getString("hull_gear_class"),
            resultSet.getString("group_name"),
            resultSet.getBoolean("is_jip"),
            parseNullDate(resultSet.getString("death")),
            resultSet.getFloat("death_ingame")
        )
    }
}