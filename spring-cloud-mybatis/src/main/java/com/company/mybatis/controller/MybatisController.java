package com.company.mybatis.controller;

import com.company.mybatis.pojo.BookInfo;
import com.company.mybatis.pojo.BookUser;
import com.company.mybatis.service.master.BookUserService;
import com.company.mybatis.service.slave.BookInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@RestController
@Slf4j
public class MybatisController {

    @Autowired
    private BookUserService bookUserService;

    @Autowired
    private BookInfoService bookInfoService;

    @PostMapping(value = "/mybatis/master/get")
    public ResponseEntity<BookUser> mybatisMaster(){
        BookUser bookUser = bookUserService.get(10);
        return ResponseEntity.ok(bookUser);
    }

    @PostMapping(value = "/mybatis/slave/get")
    public ResponseEntity<BookInfo> mybatisSlave(){
        BookInfo bookInfo = bookInfoService.get(1);
        return ResponseEntity.ok(bookInfo);
    }

    @PostMapping(value = "/mybatis/slave/save")
    public ResponseEntity<BookInfo> mybatisSlaveSave(){
        BookInfo bookInfo = BookInfo.builder()
                .bookName("Java编程思想")
                .bookAuthor("作者")
                .bookDesc("描述")
                .bookNum(100)
                .bookPrice(new BigDecimal("100.1"))
                .bookType(1)
                .onLine(1)
                .build();
        bookInfoService.save(bookInfo);
        BookInfo b = bookInfoService.get(bookInfo.getId());
        return ResponseEntity.ok(b);
    }
}
