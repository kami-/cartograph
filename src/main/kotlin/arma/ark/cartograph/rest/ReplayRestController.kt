package arma.ark.cartograph.rest

import arma.ark.cartograph.domain.Replay
import arma.ark.cartograph.rest.exception.ReplayNotFoundException
import arma.ark.cartograph.service.ReplayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/replay")
public class ReplayRestController @Autowired constructor(val replayService: ReplayService) {
    @RequestMapping(value = "/{missionId}", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    public fun getByMissionId(@PathVariable missionId: Int): Replay {
        return replayService.getByMissionId(missionId)
                ?: throw ReplayNotFoundException("No mission was found with id '$missionId' for replay!")
    }
}