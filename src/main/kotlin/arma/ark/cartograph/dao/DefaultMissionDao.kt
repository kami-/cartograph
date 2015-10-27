package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.Mission
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DefaultMissionDao @Autowired constructor(val jdbcTemplate: JdbcTemplate) : MissionDao {
    override fun getAll(): List<Mission> {
        val sql = "SELECT * FROM mission"
        return jdbcTemplate.query(sql, { rs, rn -> mapMission(rs) })
    }

    private fun mapMission(resultSet: ResultSet): Mission {
        return Mission(
            resultSet.getInt("id"),
            resultSet.getString("created"),
            resultSet.getString("mission_name"),
            resultSet.getString("world_name"),
            resultSet.getString("safety_timer"),
            resultSet.getInt("safety_timer_ingame"),
            resultSet.getString("end"),
            resultSet.getInt("end_ingame")
        )
    }
}