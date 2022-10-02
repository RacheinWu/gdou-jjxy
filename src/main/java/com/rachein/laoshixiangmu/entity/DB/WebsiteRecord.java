package com.rachein.laoshixiangmu.entity.DB;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description 网页浏览次数记录 DB类
 */
@Data
@TableName("website_record")
public class WebsiteRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long websiteId;
    private Integer count;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
