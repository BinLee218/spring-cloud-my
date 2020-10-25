package com.company.mybatis.dao;

import com.company.mybatis.dto.UserPage;
import com.company.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer authId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByUserName(@Param("userName") String username);

    List<User> getAllByPage(UserPage userPage);
}
