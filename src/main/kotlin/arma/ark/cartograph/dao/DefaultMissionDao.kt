package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.Mission
import arma.ark.cartograph.util.date.DATE_FORMATTER
import arma.ark.cartograph.util.date.parseNullDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DefaultMissionDao @Autowired constructor(val jdbcTemplate: JdbcTemplate) : MissionDao {
    override fun getAll(): List<Mission> {
        val sql = "SELECT * FROM mission ORDER BY created DESC"
        return jdbcTemplate.query(sql, { rs, rn -> mapMission(rs) })
    }

    private fun mapMission(resultSet: ResultSet): Mission {
        return Mission(
            resultSet.getInt("id"),
            DATE_FORMATTER.parseDateTime(resultSet.getString("created")),
            resultSet.getString("mission_name"),
            resultSet.getString("world_name"),
            parseNullDate(resultSet.getString("safety_timer")),
            resultSet.getInt("safety_timer_ingame"),
            parseNullDate(resultSet.getString("end")),
            resultSet.getInt("end_ingame")
        )
    }
}