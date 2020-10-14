package com.company.mybatis.shiro.filter;

import com.company.mybatis.pojo.User;
import com.company.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author bin.li
 * @date 2018/8/27
 * AccessControlFilter提供了访问控制的基础功能；比如是否允许访问/当访问拒绝时如何处理等：
 * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
 * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
 */
@Slf4j
public class CaptchaValidateFilter extends AccessControlFilter {


    private String failureKeyAttribute = "shiroLoginFailure";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.info(" =============== " + getClass().getSimpleName() + ":isAccessAllowed ===============");
//        String redisCaptcha = "captcha:";
//        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
//        if (!"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
//            return true;
//        }
//        String parameter = request.getParameter("captcha");
//        Object captchaId1 = SecurityUtils.getSubject().getSession().getAttribute("captchaId");
//        if (captchaId1 == null) {
//            return false;
//        }
//        String captchaId = captchaId1.toString();
//        redisCaptcha = redisCaptcha + captchaId;
//        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
//        String code = valueOperations.get(redisCaptcha);
//        log.info("用户输入的验证码为：" + parameter + "，系统验证码为：" + code.toUpperCase());
//        return parameter.toUpperCase().equals(code.toUpperCase());

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info(" =============== " + getClass().getSimpleName() + ":onAccessDenied ===============");
        request.setAttribute(failureKeyAttribute, "验证码错误");
        return true;
    }

}
