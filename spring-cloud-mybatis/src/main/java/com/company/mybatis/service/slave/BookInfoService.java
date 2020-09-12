package com.company.mybatis.service.slave;

import com.company.mybatis.dao.slave.BookInfoDao;
import com.company.mybatis.pojo.BookInfo;
import com.company.mybatis.pojo.BookUser;
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

    public void save(BookInfo bookInfo){
        bookInfoDao.insertSelective(bookInfo);
    }
}
