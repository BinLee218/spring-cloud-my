package com.company.mybatis.dao;

import com.company.mybatis.dto.RolePage;
import com.company.mybatis.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    int deleteByPrimaryKey(Integer roleId);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    List<Role> getAllByPage(RolePage rolePage);

    List<Role> selectActiveAll();

}
