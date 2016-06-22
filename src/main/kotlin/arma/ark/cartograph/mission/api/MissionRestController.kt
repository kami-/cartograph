package arma.ark.cartograph.mission.api

import arma.ark.cartograph.mission.data.Mission
import arma.ark.cartograph.mission.data.MissionDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/mission")
public class MissionRestController @Autowired constructor(val missionDao: MissionDao) {
    @RequestMapping(value = "", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    public fun getAll(): List<Mission> {
        return missionDao.getAll()
    }
}