package com.yuxia.sbdemo.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
* @Description:  IP的工具类
* @Author:  xiaolei
* @Date:  on  2019/2/15 11:28
*/
public class IPUtil {
    /**
     * 获取真实IP地址
     * @param request
     * @return
     */
    public static String getRealIP(HttpServletRequest request) {
        String localIP = "127.0.0.1";
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
