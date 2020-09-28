package com.company.sharding.service;

import com.company.sharding.dao.BookUserDao;
import com.company.sharding.pojo.BookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class BookUserService {

    @Autowired
    private BookUserDao bookUserDao;
    @Autowired
    private TransactionTemplate masterTransactionTemplate;

    public BookUser get(Integer id) {
        return bookUserDao.selectByPrimaryKey(id);
    }

    public int save(BookUser bookUser) {
        return bookUserDao.insertSelective(bookUser);
    }

    public List<BookUser> selectBookUser(Integer infoId) {
        return bookUserDao.selectBookUser(infoId);
    }

    public List<BookUser> selectBookUser1(Long infoId) {
        return bookUserDao.selectBookUser1(infoId);
    }

    public List<BookUser> selectBookUser2(Integer infoId) {
        return bookUserDao.selectBookUser2(infoId);
    }

}
