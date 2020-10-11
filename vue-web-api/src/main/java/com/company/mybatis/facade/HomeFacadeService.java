package com.company.mybatis.facade;

import com.company.mybatis.controller.response.MenuResponse;
import com.company.mybatis.dto.Menu;
import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.pojo.RoleAuth;
import com.company.mybatis.pojo.User;
import com.company.mybatis.pojo.UserRole;
import com.company.mybatis.service.AuthService;
import com.company.mybatis.service.RoleAuthService;
import com.company.mybatis.service.RoleService;
import com.company.mybatis.service.UserRoleService;
import com.company.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public MenuResponse getMenuByUserName(String userName) {
        User user = userService.findUserByName(userName);
        if (Objects.nonNull(user)) {
            Integer roleId = userRoleService.findRoleByUserId(user.getUserId());
            Role role = roleService.selectByPrimaryKey(roleId);
            List<Integer> roleAuthIds = roleAuthService.findAuthByRoleId(role.getRoleId());
            List<Auth> auths = authService.findAuthByIds(roleAuthIds);
            List<Menu> menus = new ArrayList<>();
            for (Auth auth : auths) {
                List<Menu> childrens = new ArrayList<>();
                if (auth.getRoot() == 1 && auth.getState() == 1) {
                    List<Auth> childrenAuth = authService.findChildrenById(auth.getAuthId());
                    for (Auth auth1 : childrenAuth) {
                        Menu menu1 = Menu.builder()
                                .name(auth1.getAuthName())
                                .path(auth1.getPath())
                                .build();
                        childrens.add(menu1);
                    }
                }
                Menu menu = Menu.builder()
                        .name(auth.getAuthName())
                        .path(auth.getPath())
                        .children(childrens)
                        .build();
                menus.add(menu);
            }
            return MenuResponse.builder().menus(menus).build();
        }
        return null;
    }
}
