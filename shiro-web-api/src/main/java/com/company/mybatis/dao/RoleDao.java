package com.company.mybatis.dao;

import com.company.mybatis.controller.request.RoleRequest;
import com.company.mybatis.dto.PageParam;
import com.company.mybatis.dto.RolePage;
import com.company.mybatis.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAllByPage(RolePage rolePage);
}
