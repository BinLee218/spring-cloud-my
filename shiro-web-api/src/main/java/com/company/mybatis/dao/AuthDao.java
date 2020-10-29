package com.company.mybatis.dao;

import com.company.mybatis.dto.AuthPage;
import com.company.mybatis.pojo.Auth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthDao {
    int deleteByPrimaryKey(Integer authId);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(Auth record);

    List<Auth> findAuthByIds(@Param("authValues") List<String> authValues);

    List<Auth> findChildrenById(@Param("authId")Integer authId);

    List<Auth> findAllAuthByIds(@Param("roleAuthIds") List<Integer> roleAuthIds);

    List<Auth> findAuthByAppId(@Param("appId") Integer appId);

    List<Auth> getAllByPage(AuthPage authPage);
}
