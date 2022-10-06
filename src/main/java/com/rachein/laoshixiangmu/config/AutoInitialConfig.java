package com.rachein.laoshixiangmu.config;

import com.rachein.laoshixiangmu.entity.DB.Website;
import com.rachein.laoshixiangmu.entity.DB.WebsiteRecord;
import com.rachein.laoshixiangmu.redis.RedisService;
import com.rachein.laoshixiangmu.redis.myPrefixKey.WebsiteKey;
import com.rachein.laoshixiangmu.service.IWebsiteRecordService;
import com.rachein.laoshixiangmu.service.IWebsiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description 开启服务器后 自动初始化数据类
 */
//@Configuration
@Slf4j
public class AutoInitialConfig {

    @Autowired
    private IWebsiteRecordService websiteRecordService;

    @Autowired
    private IWebsiteService websiteService;

    @Autowired
    private RedisService redisService;

//    @Bean
    public void initR() {
        websiteRecordInit();        //初始化记录浏览量的模块
    }


    /**
     * 对于记录浏览量的模块，需要读取数据库的总操作数，复制到redis中：
     * 如果记录表中 不存在对应的网页记录，那么就初始化一个进去;
     */
//    @Bean
    public void websiteRecordInit() {
        log.info(">>>>>>>>>>>>>>>>>>>>>> 网页记录初始化【开始】");
        //获取网页列表：
        List<Long> websiteIds = websiteService.lambdaQuery()
                .select(Website::getId)
                .list()
                .stream()
                .map(Website::getId)
                .collect(Collectors.toList());
        //从记录表中找到记录数，如果不存在那么就添加数据库表；
        for (Long websiteId : websiteIds) {
            Integer count = null;
            try {
                count = websiteRecordService.lambdaQuery()
                        .eq(WebsiteRecord::getId, websiteId)
                        .one()
                        .getCount();
            } catch (NullPointerException e) {
                count = 0;
                WebsiteRecord websiteRecord = new WebsiteRecord();
                websiteRecord.setWebsiteId(websiteId);
                websiteRecord.setCount(count);
                websiteRecordService.save(websiteRecord);
                log.error(">>>>>>>>>>>>>> 插入新的网页的记录，成功！");
            }
            //redis中添加记录:
            redisService.set(WebsiteKey.TOTAL, websiteId.toString(), count);
            log.info("网页记录初始化【结束】<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }
}
