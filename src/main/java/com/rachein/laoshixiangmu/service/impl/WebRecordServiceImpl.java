package com.rachein.laoshixiangmu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.laoshixiangmu.entity.DB.Website;
import com.rachein.laoshixiangmu.entity.DB.WebsiteRecord;
import com.rachein.laoshixiangmu.mapper.WebsiteRecordMapper;
import com.rachein.laoshixiangmu.redis.RedisService;
import com.rachein.laoshixiangmu.redis.myPrefixKey.WebsiteRecordKey;
import com.rachein.laoshixiangmu.service.IWebsiteRecordService;
import com.rachein.laoshixiangmu.service.IWebsiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description
 */
@Service
@Transactional
@Slf4j
public class WebRecordServiceImpl extends ServiceImpl<WebsiteRecordMapper, WebsiteRecord> implements IWebsiteRecordService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IWebsiteService websiteService;

    @Override
    public void saveByName(String name) {
        //先根据网页的名称作为索引，从数据库中找到对应的id:
        Long id = websiteService.lambdaQuery().eq(Website::getName, name).select(Website::getId).one().getId();
        //先从redis中进行修改【因为是频繁操作，所以不要使用关系型数据库进行即时存储】
        log.info("+1");
        redisService.incr(WebsiteRecordKey.TOTAL, id.toString()); // key = id
        //将添加的操作，加入到队列中，进行排队
    }

    @Override
    public void getByName(String name) {
        //从redis中获取即可:

    }
}
