package com.huige.Institution.util.ip;

import cn.hutool.core.util.StrUtil;
import com.huige.Institution.util.CharsetKit;
import com.huige.Institution.util.http.HttpUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 获取地址类
 *
 * @author hying
 */
public class AddressUtil {
    private static final Logger log = LoggerFactory.getLogger(AddressUtil.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        String address = UNKNOWN;
        // 内网不查询
        if (IpUtil.internalIp(ip)) {
            return "内网IP";
        }
        try {
            String respStr = HttpUtil.sendGet(IP_URL, "ip=" + ip + "&json=true", CharsetKit.GBK);
            if (StrUtil.isEmpty(respStr)) {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }

            JSONObject obj = new JSONObject(respStr);
            String region = obj.optString("pro");
            String city = obj.optString("city");
            return String.format("%s %s", region, city);
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", ip);
        }
        return address;
    }
}
