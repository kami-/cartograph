package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.Player

interface PlayerDao {
    fun getAllByMissionId(missionId: Int): List<Player>
}