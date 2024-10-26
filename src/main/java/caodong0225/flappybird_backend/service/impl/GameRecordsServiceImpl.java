package caodong0225.flappybird_backend.service.impl;

import caodong0225.flappybird_backend.entity.GameRecords;
import caodong0225.flappybird_backend.mapper.GameRecordsMapper;
import caodong0225.flappybird_backend.service.IGameRecordsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public IPage<GameRecords> getGameRecordsList(Integer page, Integer size, String sort, String order, String appId) {
        QueryWrapper<GameRecords> queryWrapper = new QueryWrapper<>();
        // 添加排序条件
        if ("asc".equalsIgnoreCase(order)) {
            queryWrapper.orderByAsc(sort);
        } else {
            queryWrapper.orderByDesc(sort);
        }
        // 添加查询条件
        queryWrapper.eq("app_id", appId);
        IPage<?> result = this.page(new Page<>(page, size), queryWrapper);
        IPage<GameRecords> challengeInfoPage = new Page<>();
        challengeInfoPage.setTotal(result.getTotal());
        challengeInfoPage.setCurrent(result.getCurrent());
        challengeInfoPage.setSize(result.getSize());
        challengeInfoPage.setPages(result.getPages());
        challengeInfoPage.setRecords((List<GameRecords>) result.getRecords());
        return challengeInfoPage;
    }


}
