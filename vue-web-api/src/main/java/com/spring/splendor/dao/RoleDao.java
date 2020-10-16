package com.spring.splendor.dao;

import com.spring.splendor.pojo.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
