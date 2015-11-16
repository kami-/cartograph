package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.SimpleMission
import arma.ark.cartograph.domain.Mission
import arma.ark.cartograph.util.world.WORLDS
import arma.ark.cartograph.util.date.DATE_FORMATTER
import arma.ark.cartograph.util.date.isDuringSession
import arma.ark.cartograph.util.date.parseNullDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DefaultMissionDao @Autowired constructor(val jdbcTemplate: JdbcTemplate) : MissionDao {
    override fun getAllSimple(): List<SimpleMission> {
        return getAll().map { SimpleMission(it.id, it.created, it.name, it.world.name) }
    }

    override fun getAll(): List<Mission> {
        val sql = "SELECT * FROM mission ORDER BY created DESC"
        return jdbcTemplate.query(sql, { rs, rn -> mapMission(rs) }).filter { isDuringSession(it.created) }
    }

    override fun getById(id: Int): Mission? {
        val sql = "SELECT * FROM mission WHERE id = ?"
        val missions = jdbcTemplate.query(sql, arrayOf(id), { rs, rn -> mapMission(rs) })
        return when(missions.isEmpty()) {
            true -> null
            else -> missions[0]
        }
    }

    private fun mapMission(resultSet: ResultSet): Mission {
        return Mission(
            resultSet.getInt("id"),
            DATE_FORMATTER.parseDateTime(resultSet.getString("created")),
            resultSet.getString("mission_name"),
            //resultSet.getString("world_name"),
            WORLDS.getOrImplicitDefault("chernarus"),
            parseNullDate(resultSet.getString("safety_timer")),
            resultSet.getFloat("safety_timer_ingame"),
            parseNullDate(resultSet.getString("end")),
            resultSet.getFloat("end_ingame")
        )
    }
}