package com.company.sharding.service;

import com.company.sharding.dao.BookInfoDao;
import com.company.sharding.pojo.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class BookInfoService {

    @Autowired
    private BookInfoDao bookInfoDao;

    public BookInfo get(Integer id){
        return bookInfoDao.selectByPrimaryKey(id);
    }
    public BookInfo selectBookInfoByIdAndType(Integer id, Integer booktype){
        return bookInfoDao.selectBookInfoByIdAndType(id, booktype);
    }
    public void save(BookInfo bookInfo){
        bookInfoDao.insertSelective(bookInfo);
    }
}
