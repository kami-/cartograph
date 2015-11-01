package arma.ark.cartograph.service

import arma.ark.cartograph.domain.Replay

interface ReplayService {
    fun getByMissionId(missionId: Int): Replay?
}