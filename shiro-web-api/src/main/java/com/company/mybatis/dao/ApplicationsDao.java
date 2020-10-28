package com.company.mybatis.dao;


import com.company.mybatis.pojo.Applications;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationsDao {

    int deleteByPrimaryKey(Integer appId);

    int insertSelective(Applications record);

    Applications selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(Applications record);

    List<Applications> selectAll();
}
