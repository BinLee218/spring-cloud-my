package com.company.dubbo.api.service;


import com.company.dubbo.api.dto.User;
import org.apache.commons.io.FileUtils;
import org.apache.dubbo.config.annotation.DubboService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author bin.li
 * @date 2020/11/13
 * 特性
 * 连接个数：多连接
 * 连接方式：短连接
 * 传输协议：HTTP
 * 传输方式：同步传输
 * 序列化：Hessian二进制序列化
 * 适用范围：传入传出参数数据包较大，提供者比消费者个数多，提供者压力较大，可传文件。
 * 适用场景：页面传输，文件传输，或与原生hessian服务互操作
 */
@DubboService(protocol = "hessian")
public class HessianServiceImpl implements HessianService {


    @Override
    public String getHessian() {
        return User.builder().userName("张三").passWord("密码").build().toString();
    }

    @Override
    public User getHessianUser(String name) {
        return User.builder().userName(name).passWord("密码").build();
    }

    @Override
    public Boolean uploanFile(byte[] fileByte) {
        try {
            FileUtils.writeByteArrayToFile(new File("/Users/bin.li/Desktop/dubboFile.txt"), fileByte);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
