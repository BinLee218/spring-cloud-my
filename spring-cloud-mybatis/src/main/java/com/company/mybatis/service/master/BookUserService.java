package com.company.mybatis.service.master;

import com.company.mybatis.dao.master.BookUserDao;
import com.company.mybatis.pojo.BookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

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

    public BookUser get(Integer id){
        BookUser bookUser = masterTransactionTemplate.execute(new TransactionCallback<BookUser>() {
            @Override
            public BookUser doInTransaction(TransactionStatus status) {
                bookUserDao.selectByPrimaryKey(id);
                return bookUserDao.selectByPrimaryKey(id);

            }
        });
        return bookUser;
    }

}
