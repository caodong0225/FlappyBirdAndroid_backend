package caodong0225.flappybird_backend.service;

import caodong0225.flappybird_backend.entity.GameRecords;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /*
     * 获取游戏记录
     */
    List<GameRecords> getGameRecords(Long timestamp, String appId);

    /*
     * 获取游戏记录列表
     */
    IPage<GameRecords> getGameRecordsList(Integer page, Integer size, String sort, String order, String appId);
}
