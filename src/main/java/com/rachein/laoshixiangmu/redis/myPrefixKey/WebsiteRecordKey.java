package com.rachein.laoshixiangmu.redis.myPrefixKey;

import com.rachein.laoshixiangmu.redis.BasePrefix;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description
 */
public class WebsiteRecordKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 0;
    public static final String NAME = "total";

    /**
     * 防止被外面实例化
     * @param expireSeconds
     * @param prefix
     */
    private WebsiteRecordKey(int expireSeconds, String prefix) {super(expireSeconds, prefix);}

    /**
     * 需要缓存的字段：
     */
    //某个网页的总浏览次数
    public static WebsiteRecordKey TOTAL = new WebsiteRecordKey(TOKEN_EXPIRE,NAME);

}
