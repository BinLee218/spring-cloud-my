package com.company.mybatis.shiro.credentials;

import com.company.mybatis.pojo.User;
import com.company.mybatis.service.UserService;
import com.company.mybatis.shiro.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author bin.li
 * @date 2018/8/27
 * Shiro提供了PasswordService及CredentialsMatcher用于提供加密密码及验证密码服务。
 */
@Slf4j
public class GeneralCredentialsMatcher implements CredentialsMatcher {

    @Autowired
    private UserService userService;

    /**
     * @param ctoken 匹配用户输入的token的凭证（未加密）
     * @param info   与系统提供的凭证（已加密）
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken ctoken, AuthenticationInfo info) {
        log.info(" =============== " + getClass().getSimpleName() + ":doCredentialsMatch ===============");
        PrincipalCollection principals = info.getPrincipals();
        LoginUser innerUser = principals.oneByType(LoginUser.class);
        UsernamePasswordToken token = (UsernamePasswordToken) ctoken;
        User user = userService.login(token.getUsername(), new String(token.getPassword()));
        if (Objects.nonNull(user) && user.getStatus() == 0) {
            innerUser.setId(user.getUserId());
            innerUser.setUserName(user.getUserName());
            innerUser.setRealName(user.getRealName());
            return true;
        }
        log.info("登录用户不存在或用户状态不正确，用户名：{}", token.getUsername());
        return false;
    }
}


