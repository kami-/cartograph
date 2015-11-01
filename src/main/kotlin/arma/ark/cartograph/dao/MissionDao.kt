package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.SimpleMission
import arma.ark.cartograph.domain.Mission

interface MissionDao {
    fun getAllSimple(): List<SimpleMission>
    fun getAll(): List<Mission>
    fun getById(id: Int): Mission?
}