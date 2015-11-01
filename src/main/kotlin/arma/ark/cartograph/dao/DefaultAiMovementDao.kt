package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.AiMovement
import arma.ark.cartograph.util.date.DATE_FORMATTER
import arma.ark.cartograph.util.position.parsePosition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DefaultAiMovementDao @Autowired constructor(val jdbcTemplate: JdbcTemplate) : AiMovementDao {
    override fun getAllByMissionId(missionId: Int): List<AiMovement> {
        val sql = "SELECT * FROM ai_movement WHERE mission_id = ?"
        return jdbcTemplate.query(sql, arrayOf(missionId), { rs, rn -> mapAiMovement(rs) })
    }

    private fun mapAiMovement(resultSet: ResultSet): AiMovement {
        return AiMovement(
            resultSet.getInt("id"),
            DATE_FORMATTER.parseDateTime(resultSet.getString("created")),
            resultSet.getFloat("created_ingame"),
            resultSet.getInt("mission_id"),
            parsePosition(resultSet.getString("position")),
            resultSet.getString("group_name"),
            resultSet.getInt("alive_count"),
            resultSet.getString("vehicle"),
            parsePosition(resultSet.getString("waypoint_position")),
            resultSet.getString("waypoint_type")
        )
    }
}