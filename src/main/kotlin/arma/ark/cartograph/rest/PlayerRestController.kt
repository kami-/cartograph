package arma.ark.cartograph.rest

import arma.ark.cartograph.dao.PlayerDao
import arma.ark.cartograph.domain.Player
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/player")
public class PlayerRestController @Autowired constructor(val playerDao: PlayerDao) {
    @RequestMapping(value = "/{missionId}", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    public fun getAllByMissionId(@PathVariable missionId: Int): List<Player> {
        return playerDao.getAllByMissionId(missionId)
    }
}