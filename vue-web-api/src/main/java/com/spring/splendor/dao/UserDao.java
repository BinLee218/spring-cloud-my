package com.spring.splendor.dao;

import com.spring.splendor.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer authId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByUserName(@Param("userName") String username);
}
