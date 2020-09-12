package com.company.mybatis.dao.slave;


import com.company.mybatis.pojo.BookInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);
}