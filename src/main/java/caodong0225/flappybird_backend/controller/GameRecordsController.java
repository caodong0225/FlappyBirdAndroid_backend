package caodong0225.flappybird_backend.controller;

import caodong0225.flappybird_backend.dto.BaseResponseDTO;
import caodong0225.flappybird_backend.dto.GameRecordsRequestDTO;
import caodong0225.flappybird_backend.service.IGameRecordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@RestController
@RequestMapping("/records")
@Tag(name = "游戏记录管理接口", description = "用来管理游戏记录的接口")
public class GameRecordsController {
    private final IGameRecordsService gameRecordsService;

    @Autowired
    public GameRecordsController(IGameRecordsService gameRecordsService) {
        this.gameRecordsService = gameRecordsService;
    }

    @PostMapping("/submit")
    @Operation(summary = "提交游戏记录", description = "提交游戏记录")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "游戏记录已经存在"),
            @ApiResponse(responseCode = "200", description = "成功"),
            @ApiResponse(responseCode = "500", description = "游戏记录插入失败")
    })
    public BaseResponseDTO submitGameRecord(@Valid @RequestBody GameRecordsRequestDTO requestDTO) {
        if(!gameRecordsService.getGameRecords(requestDTO.getTimestamp(), requestDTO.getAppId()).isEmpty()) {
            return BaseResponseDTO.makeResponse(400, "游戏记录已存在");
        } else{
            if(gameRecordsService.insertGameRecords(requestDTO.toGameRecords())) {
                return BaseResponseDTO.makeResponse(200);
            } else {
                return BaseResponseDTO.makeResponse(500, "游戏记录插入失败");
            }
        }
    }


}
