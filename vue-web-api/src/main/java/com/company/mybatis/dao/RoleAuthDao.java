package com.company.mybatis.dao;

import com.company.mybatis.pojo.RoleAuth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleAuthDao {
    int deleteByPrimaryKey(Integer authId);

    int insert(RoleAuth record);

    int insertSelective(RoleAuth record);

    RoleAuth selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(RoleAuth record);

    int updateByPrimaryKey(RoleAuth record);

    List<Integer> findAuthByRoleId(@Param("roleId") Integer roleId);

    List<Integer> findAllAuthByRoleId(@Param("roleId")Integer roleId);
}