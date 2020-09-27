package com.company.sharding.dao;


import com.company.sharding.pojo.BookInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);

    BookInfo selectBookInfoByIdAndType(@Param("bid") Integer id, @Param("bookType") Integer bookType);
}