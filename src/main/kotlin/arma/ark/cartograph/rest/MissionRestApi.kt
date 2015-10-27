package arma.ark.cartograph.rest

import arma.ark.cartograph.dao.MissionDao
import arma.ark.cartograph.domain.Mission
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mission")
public class MissionRestApi @Autowired constructor(val missionDao: MissionDao) {
    @RequestMapping("")
    @ResponseBody
    public fun getAll(): List<Mission> {
        return missionDao.getAll()
    }
}