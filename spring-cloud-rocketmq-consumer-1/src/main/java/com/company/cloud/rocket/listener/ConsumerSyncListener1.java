package com.company.cloud.rocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/9/6
 */
@Service
@RocketMQMessageListener(consumerGroup = "sync", topic = "sync", selectorExpression = "sync", messageModel = MessageModel.CLUSTERING)
@Slf4j
public class ConsumerSyncListener1 implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("topic:{}, message:{}", "sync", message);
    }
}
