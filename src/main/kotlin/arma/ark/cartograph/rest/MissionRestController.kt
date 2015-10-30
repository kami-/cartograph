package arma.ark.cartograph.rest

import arma.ark.cartograph.dao.MissionDao
import arma.ark.cartograph.domain.Mission
import arma.ark.cartograph.domain.SimpleMission
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

    @RequestMapping(value = "/simple", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    public fun getAllSimple(): List<SimpleMission> {
        return missionDao.getAllSimple()
    }
}