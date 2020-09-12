package com.company.mybatis.service.master;

import com.company.mybatis.dao.master.BookUserDao;
import com.company.mybatis.pojo.BookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class BookUserService {

    @Autowired
    private BookUserDao bookUserDao;

    public BookUser get(Integer id){
        return bookUserDao.selectByPrimaryKey(id);
    }

}
