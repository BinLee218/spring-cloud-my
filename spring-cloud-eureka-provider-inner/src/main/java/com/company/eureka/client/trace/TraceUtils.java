package com.company.eureka.client.trace;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.Getter;

/**
 * @author bin.li
 * @date 2020/11/5
 */
@Getter
public class TraceUtils {

    public static final TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<>();

}
