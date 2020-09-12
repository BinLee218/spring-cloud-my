package com.company.mybatis.dao.master;


import com.company.mybatis.pojo.BookUser;
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