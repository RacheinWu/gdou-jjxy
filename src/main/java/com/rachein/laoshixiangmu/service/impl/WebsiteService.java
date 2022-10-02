package com.rachein.laoshixiangmu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rachein.laoshixiangmu.entity.DB.Website;
import com.rachein.laoshixiangmu.mapper.WebsiteMapper;
import com.rachein.laoshixiangmu.service.IWebsiteService;
import org.springframework.stereotype.Service;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description
 */
@Service
public class WebsiteService extends ServiceImpl<WebsiteMapper, Website> implements IWebsiteService {
}
