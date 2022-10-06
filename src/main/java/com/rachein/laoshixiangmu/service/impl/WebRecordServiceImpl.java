package com.rachein.laoshixiangmu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.laoshixiangmu.entity.DB.Website;
import com.rachein.laoshixiangmu.entity.DB.WebsiteRecord;
import com.rachein.laoshixiangmu.mapper.WebsiteRecordMapper;
import com.rachein.laoshixiangmu.redis.RedisService;
import com.rachein.laoshixiangmu.redis.myPrefixKey.WebsiteKey;
import com.rachein.laoshixiangmu.service.IWebsiteRecordService;
import com.rachein.laoshixiangmu.service.IWebsiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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
        //redis中,如果没有这个网页，那么自动添加到数据库中：
//        Website website = websiteService.lambdaQuery().eq(Website::getName, name).select(Website::getId).one();
        Website website = redisService.get(WebsiteKey.WEBSITE, name, Website.class);
        if (Objects.isNull(website)) {
            //存入mysql中
            website = new Website();
            website.setName(name);
            website.setStatus(1);
            websiteService.save(website);
            //存入redis中
            redisService.set(WebsiteKey.WEBSITE, name, website);
        }
        //先根据网页的名称作为索引，从数据库中找到对应的id:
        Long id = website.getId();
        //先从redis中进行修改【因为是频繁操作，所以不要使用关系型数据库进行即时存储】
        log.info("+1");
        redisService.incr(WebsiteKey.TOTAL, id.toString()); // key = id
        //将添加的操作，加入到队列中，进行排队
    }

    @Override
    public Integer getByName(String name) {
        //从redis中获取即可:
        return redisService.get(WebsiteKey.TOTAL, name, Integer.class);
    }
}
