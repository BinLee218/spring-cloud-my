package com.company.cloud.rocket.message;

import com.company.cloud.rocket.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author bin.li
 * @date 2020/9/6
 */
@Service
@Slf4j
public class MessageProviderService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 同步消息
     * 重要的消息通知，短信通知
     * @param messages
     */
    public void sendSyncMessage(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message<String> message = new GenericMessage<String>(messages, new MessageHeaders(maps));
//        SendResult sendResult = rocketMQTemplate.syncSend("sync", message);
        SendResult sendResult = rocketMQTemplate.syncSend("sync:sync", message);
        log.info(sendResult.toString());
    }

    /**
     * 同步顺序消息
     * hashkey：Orderly_Queue  随便给的值，只要是一样的就能保证发送到同一个队列queue中，那么就能保证顺序
     * 重要的消息通知，短信通知
     * @param messages
     */
    public void sendSyncOrderlyMessage(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message<String> message = new GenericMessage<String>(messages, new MessageHeaders(maps));
        SendResult sendResult = rocketMQTemplate.syncSendOrderly("sync:orderly", message, "Orderly_Queue");
        log.info(sendResult.toString());
    }

    /**
     * 同步延时消费消息
     * org/apache/rocketmq/store/config/MessageStoreConfig.java
     * messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
     * 实例中传入3，表示10s后消费
     * 比如电商里，提交了一个订单就可以发送一个延时消息，1h后去检查这个订单的状态，如果还是未付款就取消订单释放库存。
     * @param messages
     */
    public void syncSendDelayMessage(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message<String> message = new GenericMessage<String>(messages, new MessageHeaders(maps));
        SendResult sendResult = rocketMQTemplate.syncSend("sync:delay", message, 3000, 3);
        log.info(sendResult.toString());
    }

    /**
     * 同步发送集合消息
     * @param message
     */
    public void syncSendListMessage(List<String> message){
        List<Message<String>> listMessages = new ArrayList<>();
        for (String s : message) {
            HashMap<String, Object> maps = new HashMap<>();
            Message<String> messageString = new GenericMessage<String>(s, new MessageHeaders(maps));
            listMessages.add(messageString);
        }
        SendResult sendResult = rocketMQTemplate.syncSend("sync:collection", listMessages);
        log.info(sendResult.toString());
    }

    /**
     * 发送异步消息
     * 异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
     * @param messages
     */
    public void sendAsyncMessage(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message message = new GenericMessage(messages, new MessageHeaders(maps));
        rocketMQTemplate.asyncSend("async",message , new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info(sendResult.toString());
            }
            @Override
            public void onException(Throwable e) {
                log.info(e.toString());
            }
        });
    }

    /**
     * 发送异步顺序消息
     * 异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
     * @param messages
     */
    public void sendAsyncOrderlyMessage(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message message = new GenericMessage(messages, new MessageHeaders(maps));
        rocketMQTemplate.asyncSendOrderly("async:orderly",message , "hash_key" , new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info(sendResult.toString());
            }
            @Override
            public void onException(Throwable e) {
                log.info(e.toString());
            }
        });
    }

    /**
     * 单向发送消息
     * 这种方式主要用在不特别关心发送结果的场景，例如日志发送。
     */
    public void sendOneWay(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message<String> message = new GenericMessage<String>(messages, new MessageHeaders(maps));
        rocketMQTemplate.sendOneWay("oneway", message);
    }


    /**
     *  同步发送request并且等待user类型的返回值
     * @param messages
     */
    public User sendSyncAndReceive(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message<String> message = new GenericMessage<String>(messages, new MessageHeaders(maps));
        return rocketMQTemplate.sendAndReceive("sync:sendReceive", message, User.class);
    }

    /**
     *  异步发送request并且等待user类型的返回值
     * @param messages
     */
    public void sendAsyncAndReceive(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message<String> message = new GenericMessage<String>(messages, new MessageHeaders(maps));
        rocketMQTemplate.sendAndReceive("async:sendReceive", message, new RocketMQLocalRequestCallback<User>() {
            @Override
            public void onSuccess(User user) {
                log.info(user.toString());
            }
            @Override
            public void onException(Throwable e) {
                log.info("我出错了呢：{}" , e.toString());
            }
        });
    }


    /**
     * 同步发送request并且等待user类型的返回值
     * @see com.company.cloud.rocket.controller.TransactionListener
     * @param messages
     */
    public void sendMessageInTransaction(String messages){
        HashMap<String, Object> maps = new HashMap<>();
        Message<String> message = new GenericMessage<String>(messages, new MessageHeaders(maps));
        TransactionSendResult transaction = rocketMQTemplate.sendMessageInTransaction("transaction", message, "2");
        log.info(transaction.toString());
    }
}

