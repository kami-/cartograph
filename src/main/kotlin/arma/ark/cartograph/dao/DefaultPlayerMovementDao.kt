package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.PlayerMovement
import arma.ark.cartograph.util.date.DATE_FORMATTER
import arma.ark.cartograph.util.position.parsePosition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DefaultPlayerMovementDao @Autowired constructor(val jdbcTemplate: JdbcTemplate) : PlayerMovementDao {
    override fun getAllByMissionId(missionId: Int): List<PlayerMovement> {
        val sql = """SELECT pm.*
            FROM player_movement pm
            INNER JOIN player p
                ON pm.player_id = p.id
            WHERE mission_id = ?"""
        return jdbcTemplate.query(sql, arrayOf(missionId), { rs, rn -> mapPlayerMovement(rs) })
    }

    private fun mapPlayerMovement(resultSet: ResultSet): PlayerMovement {
        return PlayerMovement(
            resultSet.getInt("id"),
            DATE_FORMATTER.parseDateTime(resultSet.getString("created")),
            resultSet.getFloat("created_ingame"),
            resultSet.getInt("player_id"),
            parsePosition(resultSet.getString("position")),
            resultSet.getString("vehicle")
        )
    }
}