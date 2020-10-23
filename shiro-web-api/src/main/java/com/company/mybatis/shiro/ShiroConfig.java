package com.company.mybatis.shiro;

import com.company.mybatis.shiro.credentials.GeneralCredentialsMatcher;
import com.company.mybatis.shiro.filter.CaptchaValidateFilter;
import com.company.mybatis.shiro.filter.JwtFilter;
import com.company.mybatis.shiro.jwt.JwtSessionIdGenerator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroConfig {

    /*-------------------------------------------
    |             哈              哈             |
    ============================================*/
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 验证码验证filter
     *
     * @return
     */
    @Bean(name = "captchaValidate")
    public CaptchaValidateFilter captchaValidate() {
        return new CaptchaValidateFilter();
    }
 /**
     * 验证码验证filter
     *
     * @return
     */
    @Bean(name = "jwt")
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    /**
     * 登录时账户密码验证
     *
     * @return
     */
    @Bean
    public GeneralCredentialsMatcher generalCredentialsMatcher() {
        return new GeneralCredentialsMatcher();
    }

    /**
     * 账户验证，权限验证
     *
     * @return
     */
    @Bean
    public UserRealm myRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(generalCredentialsMatcher());
        return userRealm;
    }


    @Bean(name = "shiroFilterChainDefinition")
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/api/user/login", "captchaValidate");
        definition.addPathDefinition("/api/**", "jwt");
        return definition;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        manager.setSessionManager(defaultWebSessionManager());
        manager.setCacheManager(redisCacheManager());
        return manager;
    }
    @Bean
    public RedisManager redisManager() {
        return new RedisManager();
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
//        session的失效时长，单位毫秒 1小时: 3600000
//        设置全局会话超时时间，默认30分钟，即如果30分钟内没有访问会话将过期 1800000
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
//        删除失效的session
        defaultWebSessionManager.setDeleteInvalidSessions(true);
//        是否开启会话验证器，默认是开启的
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
//        Shiro提供了会话验证调度器，用于定期的验证会话是否已过期，如果过期将停止会话；
//        出于性能考虑，一般情况下都是获取会话时来验证会话是否过期并停止会话的；
//        但是如在web环境中，如果用户不主动退出是不知道会话是否过期的，因此需要定期的检测会话是否过期，
//        Shiro提供了会话验证调度器SessionValidationScheduler来做这件事情。
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(18000);
        scheduler.setSessionManager(defaultWebSessionManager);
        defaultWebSessionManager.setSessionValidationScheduler(scheduler);
//        Shiro提供SessionDAO用于会话的CRUD
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setSessionIdGenerator(new JwtSessionIdGenerator());
        redisSessionDAO.setRedisManager(redisManager());
        defaultWebSessionManager.setSessionDAO(redisSessionDAO);
//        是否启用/禁用Session Id Cookie，默认是启用的；
//        如果禁用后将不会设置Session Id Cookie，即默认使用了Servlet容器的JSESSIONID，
//        且通过URL重写（URL中的“;JSESSIONID=id”部分）保存Session Id。
        defaultWebSessionManager.setSessionIdCookieEnabled(false);
        return defaultWebSessionManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
