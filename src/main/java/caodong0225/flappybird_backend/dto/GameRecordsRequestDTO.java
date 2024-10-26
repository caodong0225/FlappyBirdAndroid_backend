package caodong0225.flappybird_backend.dto;

import caodong0225.flappybird_backend.entity.GameRecords;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@Getter
@Setter
@Schema(name = "GameRecordsRequestDTO", description = "提交游玩记录的请求体")
public class GameRecordsRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * APP的ID
     */
    @Schema(description = "app的唯一标识字符", type = "string", example = "098f6bcd4621d373cade4e832627b4f6")
    private String appId;

    /**
     * 分数
     */
    @Schema(description = "游戏的分数", type = "integer", example = "1")
    private Integer score;

    /**
     * 游戏时间
     */
    @Schema(description = "游戏的时间，时间戳格式，单位是毫秒", type = "long", example = "1729943022123")
    private Long timestamp;

    /**
     * 游戏的地址
     */
    @Schema(description = "游玩时候的地址", type = "string", example = "军工路上海理工大学580先进制造大楼旁")
    private String location;

    /**
     * 游戏的经度
     */
    @Schema(description = "游玩的经度", type = "string", example = "121")
    private String latitude;

    /**
     * 游玩时候的维度
     */
    @Schema(description = "游玩的纬度", type = "string", example = "31")
    private String longitude;

    /**
     * 游玩的持续时间
     */
    @Schema(description = "本局游戏的持续时间", type = "long", example = "31")
    private Long duration;

    /**
     * 评价
     */
    @Schema(description = "备注", type = "string", example = "")
    private String remark;

    public GameRecords toGameRecords() {
        GameRecords gameRecords = new GameRecords();
        gameRecords.setAppId(appId);
        gameRecords.setScore(score);
        gameRecords.setTimestamp(timestamp);
        gameRecords.setLocation(location);
        gameRecords.setLatitude(latitude);
        gameRecords.setLongitude(longitude);
        gameRecords.setDuration(duration);
        gameRecords.setRemark(remark);
        return gameRecords;
    }
}
