package com.rachein.laoshixiangmu.controller;

import com.rachein.laoshixiangmu.service.IWebsiteRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description 操作记录 模块
 */
@Api(tags = "操作记录模块")
@RestController
public class RecordController {

    @Autowired
    private IWebsiteRecordService websiteService;


    @ApiOperation("记录某个网页的浏览量")
    @GetMapping("/record/count/add/{resource}")
    public void recordWebsite(@PathVariable String resource) {
        websiteService.saveByName(resource);
    }


    @ApiOperation("获取某个网页的浏览量")
    @GetMapping("/record/count/{name}")
    public void getByName(@PathVariable("name") String name) {
        websiteService.getByName(name);
    }

//    @ApiOperation("获取某文件的下载量")
//    @GetMapping("/")

}
