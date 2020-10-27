package com.company.mybatis.dao;

import com.company.mybatis.pojo.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao {
    int deleteByPrimaryKey(Integer authId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    Integer findRoleByUserId(@Param("userId") Integer userId);

    UserRole findByUserId(@Param("userId") Integer userId);

}
