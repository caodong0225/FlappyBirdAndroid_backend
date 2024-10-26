package caodong0225.flappybird_backend.service.impl;

import caodong0225.flappybird_backend.entity.GameRecords;
import caodong0225.flappybird_backend.mapper.GameRecordsMapper;
import caodong0225.flappybird_backend.service.IGameRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@Service
public class GameRecordsServiceImpl extends ServiceImpl<GameRecordsMapper, GameRecords> implements IGameRecordsService {
    @Override
    public boolean insertGameRecords(GameRecords gameRecords) {
        return this.save(gameRecords);
    }

    @Override
    public List<GameRecords> getGameRecords(Long timestamp, String appId) {
        return this.lambdaQuery().eq(GameRecords::getAppId, appId).ge(GameRecords::getTimestamp, timestamp).list();
    }


}
