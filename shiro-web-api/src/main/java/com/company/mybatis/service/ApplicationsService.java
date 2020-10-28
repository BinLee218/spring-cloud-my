package com.company.mybatis.service;

import com.company.mybatis.dao.ApplicationsDao;
import com.company.mybatis.pojo.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/10/28
 */
@Service
public class ApplicationsService {

    @Autowired
    private ApplicationsDao applicationsDao;


    public List<Applications> selectAll() {
        return applicationsDao.selectAll();
    }
}
