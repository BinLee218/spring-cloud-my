package com.company.dubbo.api.service;

import com.company.dubbo.api.dto.User;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author bin.li
 * @date 2020/11/10
 * 【特性】
 * 缺省协议，使用基于 mina 1.1.7 和 hessian 3.2.1 的 tbremoting 交互。
 * 连接个数：单连接
 * 连接方式：长连接
 * 传输协议：TCP
 * 传输方式：NIO 异步传输
 * 序列化：Hessian 二进制序列化
 * 适用范围：传入传出参数数据包较小（建议小于100K），消费者比提供者个数多，单一消费者无法压满提供者，尽量不要用 dubbo 协议传输大文件或超大字符串。
 * 适用场景：常规远程服务方法调用
 * 【约束】
 * 参数及返回值需实现 Serializable 接口
 * 参数及返回值不能自定义实现 List, Map, Number, Date, Calendar 等接口，只能用 JDK 自带的实现，因为 hessian 会做特殊处理，自定义实现类中的属性值都会丢失。
 * Hessian 序列化，只传成员属性值和值的类型，不传方法或静态变量，兼容情况
 */
@DubboService(protocol = "dubbo")
public class UserServiceImpl implements UserService {


    @Override
    public User getUser() {
        return User.builder().userName("张三").passWord("密码").build();
    }

    @Override
    public Boolean createUser() {
        return true;
    }
}
