package arma.ark.cartograph.service

import arma.ark.cartograph.dao.AiMovementDao
import arma.ark.cartograph.dao.MissionDao
import arma.ark.cartograph.dao.PlayerDao
import arma.ark.cartograph.dao.PlayerMovementDao
import arma.ark.cartograph.domain.Replay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DefaultReplayService @Autowired constructor(
        val missionDao: MissionDao,
        val playerDao: PlayerDao,
        val playerMovementDao: PlayerMovementDao,
        val aiMovementDao: AiMovementDao) : ReplayService {

    override fun getByMissionId(missionId: Int): Replay? {
        val mission = missionDao.getById(missionId) ?: return null
        val playerMovements = playerMovementDao.getAllByMissionId(missionId).sortedBy { it.createdIngame.toInt() }
        val aiMovements = aiMovementDao.getAllByMissionId(missionId).sortedBy { it.createdIngame.toInt() }
        return Replay(mission, playerDao.getAllByMissionId(missionId), playerMovements, aiMovements)
    }
}