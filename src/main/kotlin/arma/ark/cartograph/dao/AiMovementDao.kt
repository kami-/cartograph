package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.AiMovement

interface AiMovementDao {
    fun getAllByMissionId(missionId: Int): List<AiMovement>
}