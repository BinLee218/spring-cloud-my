package com.company.mybatis.dao;

import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.RoleAuth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleAuthDao {
    int deleteByPrimaryKey(Integer authId);

    int insertSelective(RoleAuth record);

    RoleAuth selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(RoleAuth record);

    List<String> findAuthByRoleId(@Param("roleId") Integer roleId);

    List<Auth> findByRoleId(@Param("roleId") Integer roleId);

    void deleteRoleAuthByRoleId(@Param("roleId") Integer roleId);
}
