package caodong0225.flappybird_backend.service;

import caodong0225.flappybird_backend.entity.GameRecords;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
public interface IGameRecordsService extends IService<GameRecords> {
    /*
     * 插入游戏记录
     */
    boolean insertGameRecords(GameRecords gameRecords);

    List<GameRecords> getGameRecords(Long timestamp, String appId);
}
