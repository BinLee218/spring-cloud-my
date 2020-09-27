package com.company.sharding.test;

import com.company.sharding.SpringMybatisShardingApplication;
import com.company.sharding.pojo.BookInfo;
import com.company.sharding.pojo.BookUser;
import com.company.sharding.service.BookInfoService;
import com.company.sharding.service.BookUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @author bin.li
 * @date 2020/9/26
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringMybatisShardingApplication.class)
public class ShardingTest {

    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private BookUserService bookUserService;

    @Test
    public void TestBookInfo() {
        BookInfo bookInfo0 = BookInfo.builder()
                .bookName("Java编程思想111")
                .bookAuthor("作者111")
                .bookDesc("描述111")
                .bookNum(1001)
                .bookPrice(new BigDecimal("100.1"))
                .bookType(0)
                .onLine(1)
                .build();
        bookInfoService.save(bookInfo0);

        BookInfo bookInfo1 = BookInfo.builder()
                .bookName("Java编程思想111")
                .bookAuthor("作者111")
                .bookDesc("描述111")
                .bookNum(1001)
                .bookPrice(new BigDecimal("100.1"))
                .bookType(1)
                .onLine(1)
                .build();
        bookInfoService.save(bookInfo1);

    }

    @Test
    public void TestBookInfoGet() {
        BookInfo bookInfo = bookInfoService.selectBookInfoByIdAndType(16, 1);
        System.out.println(bookInfo.toString());
    }

    @Test
    public void TestBookUser() {
        BookUser bookUser0 = BookUser
                .builder()
                .userName("userName")
                .userLoginName("loginName")
                .salt("slat")
                .userPassword("password")
                .userEmail("email")
                .systemUser(0)
                .build();
        bookUserService.save(bookUser0);

        BookUser bookUser1 = BookUser
                .builder()
                .userName("userName")
                .userLoginName("loginName")
                .salt("slat")
                .userPassword("password")
                .userEmail("email")
                .systemUser(1)
                .build();
        bookUserService.save(bookUser1);

        BookUser bookUser2 = BookUser
                .builder()
                .userName("userName")
                .userLoginName("loginName")
                .salt("slat")
                .userPassword("password")
                .userEmail("email")
                .systemUser(2)
                .build();
        bookUserService.save(bookUser2);

        BookUser bookUser3 = BookUser
                .builder()
                .userName("userName")
                .userLoginName("loginName")
                .salt("slat")
                .userPassword("password")
                .userEmail("email")
                .systemUser(3)
                .build();
        bookUserService.save(bookUser3);
    }
}
