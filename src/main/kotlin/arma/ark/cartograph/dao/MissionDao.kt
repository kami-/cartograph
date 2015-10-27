package arma.ark.cartograph.dao

import arma.ark.cartograph.domain.Mission

interface MissionDao {
    fun getAll(): List<Mission>
}