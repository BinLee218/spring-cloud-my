package com.company.sharding.dao;


import com.company.sharding.pojo.BookUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookUser record);

    int insertSelective(BookUser record);

    BookUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookUser record);

    int updateByPrimaryKey(BookUser record);

    List<BookUser> selectBookUser(@Param("infoId") Integer infoId);

    List<BookUser> selectBookUser1(@Param("infoId") Long infoId);

    List<BookUser> selectBookUser2(@Param("infoId") Integer infoId);

}