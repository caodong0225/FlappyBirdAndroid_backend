import caodong0225.flappybird_backend.FlappybirdBackendApplication;
import caodong0225.flappybird_backend.entity.GameRecords;
import caodong0225.flappybird_backend.service.IGameRecordsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FlappybirdBackendApplication.class) // 加载整个 Spring Boot 上下文
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 避免每次测试重新实例化测试类
@Transactional // 保证测试对数据库的操作会回滚
public class GameRecordsServiceTest {

    @Autowired
    private IGameRecordsService gameRecordsService;

    @Test
    @DisplayName("测试插入游戏记录")
    public void testInsertGameRecords() {
        GameRecords gameRecord = new GameRecords();
        gameRecord.setAppId("testApp");
        gameRecord.setTimestamp(System.currentTimeMillis());
        gameRecord.setScore(100);

        boolean result = gameRecordsService.insertGameRecords(gameRecord);

        assertTrue(result, "插入游戏记录失败");
        assertNotNull(gameRecord.getId(), "记录ID未生成");
    }

    @Test
    @DisplayName("测试获取游戏记录")
    public void testGetGameRecords() {
        // 插入测试数据
        GameRecords record1 = new GameRecords();
        record1.setAppId("testApp");
        record1.setTimestamp(System.currentTimeMillis() - 1000);
        record1.setScore(80);

        GameRecords record2 = new GameRecords();
        record2.setAppId("testApp");
        record2.setTimestamp(System.currentTimeMillis());
        record2.setScore(120);

        gameRecordsService.insertGameRecords(record1);
        gameRecordsService.insertGameRecords(record2);

        // 查询数据
        List<GameRecords> records = gameRecordsService.getGameRecords(System.currentTimeMillis() - 2000, "testApp");

        assertNotNull(records, "查询结果为空");
        assertEquals(2, records.size(), "查询结果数量不正确");
    }

    @Test
    @DisplayName("测试分页获取游戏记录列表")
    public void testGetGameRecordsList() {
        // 插入测试数据
        for (int i = 1; i <= 10; i++) {
            GameRecords record = new GameRecords();
            record.setAppId("testApp");
            record.setTimestamp(System.currentTimeMillis());
            record.setScore(i * 10);
            gameRecordsService.insertGameRecords(record);
        }

        // 分页查询
        IPage<GameRecords> page = gameRecordsService.getGameRecordsList(1, 5, "score", "asc", "testApp");

        assertNotNull(page, "分页查询结果为空");
        assertEquals(5, page.getRecords().size(), "分页结果数量不正确");
        assertEquals(10, page.getTotal(), "总记录数不正确");
        assertEquals(2, page.getPages(), "总页数不正确");

        // 验证排序
        assertEquals(10, page.getRecords().get(0).getScore(), "排序结果不正确");
        assertEquals(50, page.getRecords().get(4).getScore(), "排序结果不正确");
    }

    @Test
    @DisplayName("测试获取所有 appId")
    public void testGetAppIds() {
        // 插入测试数据
        GameRecords record1 = new GameRecords();
        record1.setAppId("app1");
        record1.setTimestamp(System.currentTimeMillis());
        record1.setScore(50);

        GameRecords record2 = new GameRecords();
        record2.setAppId("app2");
        record2.setTimestamp(System.currentTimeMillis());
        record2.setScore(80);

        gameRecordsService.insertGameRecords(record1);
        gameRecordsService.insertGameRecords(record2);

        Object[] appIds = gameRecordsService.getAppIds();

        assertNotNull(appIds, "获取 appId 结果为空");
        assertEquals(2, appIds.length, "appId 数量不正确");
        assertTrue(Arrays.asList(appIds).contains("app1"), "app1 不存在");
        assertTrue(Arrays.asList(appIds).contains("app2"), "app2 不存在");
    }
}