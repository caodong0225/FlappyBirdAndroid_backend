package caodong0225.flappybird_backend.controller;

import caodong0225.flappybird_backend.entity.GameRecords;
import caodong0225.flappybird_backend.service.IGameRecordsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-10-28
 */
@Controller
public class RecordsShowController {
    private final IGameRecordsService gameRecordsService;

    public RecordsShowController(IGameRecordsService gameRecordsService, Object[] appIds) {
        this.gameRecordsService = gameRecordsService;
    }

    @GetMapping("/data")
    public String hello(Model model,
                        String appId,
                        @RequestParam(defaultValue = "1") Integer current,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "timestamp") String sort,
                        @RequestParam(defaultValue = "desc") String order) {
        IPage<GameRecords> gameInfoPage = gameRecordsService.getGameRecordsList(current, size, sort, order, appId);
        Object[] appIds = gameRecordsService.getAppIds();
        model.addAttribute("appIds", appIds);
        model.addAttribute("currentAppId", appId);
        model.addAttribute("order", order);
        model.addAttribute("gameRecords", gameInfoPage.getRecords());
        model.addAttribute("currentTotal", gameInfoPage.getTotal());
        model.addAttribute("currentSize", gameInfoPage.getSize());
        model.addAttribute("currentPage", gameInfoPage.getCurrent());
        model.addAttribute("totalPages", gameInfoPage.getPages());
        return "index";
    }
}
