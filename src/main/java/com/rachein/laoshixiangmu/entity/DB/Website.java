package com.rachein.laoshixiangmu.entity.DB;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description
 */
@Data
@TableName("t_website")
public class Website {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer status;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
