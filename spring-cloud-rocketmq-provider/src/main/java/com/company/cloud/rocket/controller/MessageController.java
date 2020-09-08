package com.company.cloud.rocket.controller;

import com.company.cloud.rocket.dto.User;
import com.company.cloud.rocket.message.MessageProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author bin.li
 * @date 2020/9/6
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageProviderService messageProviderService;

    /**
     * 同步消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/send/sync")
    public ResponseEntity<String> sendSyncMessage(String message) {
        messageProviderService.sendSyncMessage(message);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 同步顺序消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/send/syncOrderly")
    public ResponseEntity<String> sendSyncOrderlyMessage(String message) {
        for (int i = 0; i < 30; i++) {
            messageProviderService.sendSyncOrderlyMessage(message + i);
        }
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 同步延时消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/send/sync/delay")
    public ResponseEntity<String> sendSyncDelayMessage(String message) {
        messageProviderService.syncSendDelayMessage(message);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 同步批量消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/send/sync/collection")
    public ResponseEntity<String> sendSyncListMessage(String message) {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lists.add(message + i);
        }
        messageProviderService.syncSendListMessage(lists);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 同步单向消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/send/oneway")
    public ResponseEntity<String> sendOneWay(String message) {
        messageProviderService.sendOneWay(message);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 异步消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/send/async")
    public ResponseEntity<String> sendAsyncMessage(String message) {
        messageProviderService.sendAsyncMessage(message);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 异步顺序消息
     * @param message
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/send/asyncOrderly")
    public ResponseEntity<String> sendAsyncOrderlyMessage(String message) throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            messageProviderService.sendAsyncOrderlyMessage(message + i);
            Thread.sleep(10);
        }
        return ResponseEntity.ok("SUCCESS");
    }

    /*********************************************************************************************************************************************************/
    /**
     *  同步发送request并且等待user类型的返回值
     * @param messages
     */
    @RequestMapping(value = "/send/sync/receive")
    public ResponseEntity<User>  sendSyncAndReceive(String messages){
        User user = messageProviderService.sendSyncAndReceive(messages);
        return ResponseEntity.ok(user);
    }

    /**
     *  同步发送request并且等待user类型的返回值
     * @param messages
     */
    @RequestMapping(value = "/send/async/receive")
    public ResponseEntity<String>  sendAsyncAndReceive(String messages){
        messageProviderService.sendAsyncAndReceive(messages);
        return ResponseEntity.ok("SUCCESS");
    }

    @RequestMapping(value = "/send/transaction")
    public ResponseEntity<String> sendMessageInTransaction(String messages){
        messageProviderService.sendMessageInTransaction(messages);
        return ResponseEntity.ok("SUCCESS");
    }

}
