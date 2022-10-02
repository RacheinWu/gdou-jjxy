package com.rachein.laoshixiangmu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rachein.laoshixiangmu.entity.DB.WebsiteRecord;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description
 */
public interface IWebsiteRecordService extends IService<WebsiteRecord> {

    /**
     * 保存浏览次数
     * @param resource 网页名称(索引1)
     */
    void saveByName(String resource);

    void getByName(String name);


}
