package com.company.mybatis.dao;

import com.company.mybatis.pojo.Auth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthDao {
    int deleteByPrimaryKey(Integer authId);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Auth> findAuthByIds(@Param("roleAuthIds") List<Integer> roleAuthIds);

    List<Auth> findChildrenById(@Param("authId")Integer authId);

    List<Auth> findAllAuthByIds(@Param("roleAuthIds") List<Integer> roleAuthIds);
}