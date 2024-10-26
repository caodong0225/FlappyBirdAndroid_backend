package caodong0225.flappybird_backend.controller;

import caodong0225.flappybird_backend.dto.BaseResponseDTO;
import caodong0225.flappybird_backend.dto.GameRecordsRequestDTO;
import caodong0225.flappybird_backend.dto.GeneralDataResponseDTO;
import caodong0225.flappybird_backend.entity.GameRecords;
import caodong0225.flappybird_backend.service.IGameRecordsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @GetMapping("/list")
    @Operation(summary = "获取游戏记录列表", description = "获取游戏记录列表")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功")
    })
    public GeneralDataResponseDTO<IPage<GameRecords>> getGameRecordsList(
            @RequestParam(defaultValue = "1") @Min(value = 1) @Parameter(name = "current", description = "页码") Integer current,
           @RequestParam(defaultValue = "10") @Min(value = 1) @Max(value = 100) @Parameter(name = "size", description = "每页大小") Integer size,
           @RequestParam(defaultValue = "timestamp")
               @Pattern(regexp = "timestamp|score|duration")
            @Parameter(name = "sort", description = "排序字段，可选值为timestamp, score, duration")
               String sort,
           @RequestParam(defaultValue = "asc")
               @Pattern(regexp = "asc|desc")
            @Parameter(name = "order", description = "排序方式，只能为asc或desc")
               String order,
            @Parameter(name = "appId", description = "APP的ID", required = true)
            @RequestParam @NotBlank @NotNull String appId
    ) {
        IPage<GameRecords> challengeInfoPage = gameRecordsService.getGameRecordsList(current, size, sort, order, appId);
        return new GeneralDataResponseDTO<>
                (challengeInfoPage);
    }

}
