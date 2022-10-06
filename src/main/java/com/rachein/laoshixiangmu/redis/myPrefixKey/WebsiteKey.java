package com.rachein.laoshixiangmu.redis.myPrefixKey;

import com.rachein.laoshixiangmu.redis.BasePrefix;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description
 */
public class WebsiteKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 0;
    public static final String RECORD_TOTAL = "record-total";
    public static final String CATEGORY = "-category";

    /**
     * 防止被外面实例化
     * @param expireSeconds
     * @param prefix
     */
    private WebsiteKey(int expireSeconds, String prefix) {super(expireSeconds, prefix);}

    /**
     * 需要缓存的字段：
     */
    //某个网页的总浏览次数
    public static WebsiteKey TOTAL = new WebsiteKey(TOKEN_EXPIRE, RECORD_TOTAL);
    public static WebsiteKey WEBSITE = new WebsiteKey(TOKEN_EXPIRE, CATEGORY);

}
