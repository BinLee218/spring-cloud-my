package com.company.eureka.client.common;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author bin.li
 * @date 2020/11/5
 */
@Getter
@Slf4j
public class LogUtil {

    public static final TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<>();
    private static final String LOGGER_ERROR = "LOGGER_ERROR";
    private static final String LOGGER_INFO = "LOGGER_INFO";

    public static void info(String msg){
        log.info(String.format("%s %s %s", ttl.get(), LOGGER_INFO, msg));
    }

    public static void error(String msg){
        log.error(String.format("%s %s %s", ttl.get(), LOGGER_ERROR, msg));
    }
}
