package com.company.mybatis.facade;

import com.company.mybatis.service.AuthService;
import com.company.mybatis.service.RoleAuthService;
import com.company.mybatis.service.RoleService;
import com.company.mybatis.service.UserRoleService;
import com.company.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/10/9
 */
@Service
public class HomeFacadeService {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAuthService roleAuthService;
    @Autowired
    private UserRoleService userRoleService;

}
