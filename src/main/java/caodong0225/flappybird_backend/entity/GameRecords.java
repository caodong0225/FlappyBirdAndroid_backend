package caodong0225.flappybird_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@Data
public class GameRecords implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "游戏记录ID", example = "1", type = "integer")
    private Integer id;

    /**
     * APP的ID
     */
    @Schema(description = "APP的ID", example = "098f6bcd4621d373cade4e832627b4f6", type = "string")
    private String appId;

    /**
     * 分数
     */
    @Schema(description = "分数", example = "100", type = "integer")
    private Integer score;

    /**
     * 游戏时间
     */
    @Schema(description = "游戏时间", example = "1729943022123")
    private Long timestamp;

    /**
     * 游戏的地址
     */
    @Schema(description = "游戏的地址", example = "地址", type = "string")
    private String location;

    /**
     * 游戏的经度
     */
    @Schema(description = "游戏的经度", example = "121", type = "string")
    private String latitude;

    /**
     * 游玩时候的维度
     */
    @Schema(description = "游戏的纬度", example = "31", type = "string")
    private String longitude;

    /**
     * 游玩的持续时间
     */
    @Schema(description = "游戏的持续时间", example = "1000", type = "long")
    private Long duration;

    /**
     * 评价
     */
    @Schema(description = "评价", example = "评价", type = "string")
    private String remark;

}
