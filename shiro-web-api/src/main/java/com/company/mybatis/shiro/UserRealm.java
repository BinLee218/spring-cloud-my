package com.company.mybatis.shiro;

import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.service.AuthService;
import com.company.mybatis.service.RoleAuthService;
import com.company.mybatis.service.RoleService;
import com.company.mybatis.service.UserRoleService;
import com.company.mybatis.service.UserService;
import com.company.mybatis.shiro.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bin.li
 * @date 2018/8/27
 * 获取身份验证信息（doGetAuthenticationInfo）及授权信息（doGetAuthorizationInfo）；
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {


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

    /**
     * doGetAuthenticationInfo获取身份验证相关信息
     * 执行时机：Subject currentUser = SecurityUtils.getSubject();
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info(" =============== " + getClass().getSimpleName() + ":doGetAuthenticationInfo ===============");
        LoginUser userInfo = new LoginUser();
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authcToken;
        return new SimpleAuthenticationInfo(userInfo, usernamePasswordToken.getPassword(),usernamePasswordToken.getUsername());
    }

    /**
     * doGetAuthorizationInfo获取授权信息
     * PrincipalCollection是一个身份集合，因为我们现在就一个Realm，
     * 所以直接调用getPrimaryPrincipal得到之前传入的用户名即可；
     * 然后根据用户名调用UserService接口获取角色及权限信息。
     * 执行时机：
     * 1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
     * 2、@RequiresRoles("admin") ：在方法上加注解的时候；
     * 3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候
     *
     * @param principals
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info(" =============== " + getClass().getSimpleName() + ":doGetAuthorizationInfo ===============");
        LoginUser innerUser = principals.oneByType(LoginUser.class);
        if (innerUser != null) {
            Integer roleId = userRoleService.findRoleByUserId(innerUser.getId());
            Role role = roleService.selectByPrimaryKey(roleId);
            List<Integer> roleAuthIds = roleAuthService.findAuthByRoleId(role.getRoleId());
            List<Auth> auths = authService.findAllAuthByIds(roleAuthIds);
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            Set<String> collect = Stream.<String>builder().add(role.getRoleValue()).build().collect(Collectors.toSet());
            authorizationInfo.setRoles(collect);
            Set<String> authValues = auths.stream().map(Auth::getAuthValue).collect(Collectors.toSet());
            authorizationInfo.setStringPermissions(authValues);
            return authorizationInfo;
        }
        throw new UnknownAccountException("权限验证出错");
    }

}
