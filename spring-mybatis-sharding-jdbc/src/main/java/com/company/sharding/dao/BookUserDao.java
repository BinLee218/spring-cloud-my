package com.company.sharding.dao;


import com.company.sharding.pojo.BookUser;
import org.springframework.stereotype.Repository;

@Repository
public interface BookUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookUser record);

    int insertSelective(BookUser record);

    BookUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookUser record);

    int updateByPrimaryKey(BookUser record);
}