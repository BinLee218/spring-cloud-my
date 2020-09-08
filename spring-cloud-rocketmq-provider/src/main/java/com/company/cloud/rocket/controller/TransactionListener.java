package com.company.cloud.rocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;


@RocketMQTransactionListener
@Slf4j
public class TransactionListener implements RocketMQLocalTransactionListener {

    /**
     * 消息发送成功回调此方法，此方法执行本地事务
     *  如果是commit的话，就直接提交事务了，消费端消费
     *  如果是ROLLBACK的话，消息被删除，消费端不会消费
     *  如果是UNKNOWN的话，会执行checkLocalTransaction方法，再二次检查本地事务，确定状态，再根据状态决定
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        if (Integer.parseInt(arg.toString()) == 1) {
            log.info("我执行了本地事务，更新的mysql数据库, 成功了");
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            log.info("我执行了本地事务，更新的mysql数据库, 失败了");
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    /**
     * 本地事务回查，跟踪日志看是2m一次
     * 并不是分布式事务，只是发送端与本地事务之间的纽带，可以确保本地事务成功后，消费端消费到信息
     * 避免了本地事务失败，消费端还会消费到信息的问题。
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info(RocketMQLocalTransactionState.COMMIT.toString());
        return RocketMQLocalTransactionState.COMMIT;
    }
}