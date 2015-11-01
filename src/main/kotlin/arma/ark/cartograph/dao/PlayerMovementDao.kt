package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.PlayerMovement

interface PlayerMovementDao {
    fun getAllByMissionId(missionId: Int): List<PlayerMovement>
}