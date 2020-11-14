package com.company.dubbo.config;

import org.apache.dubbo.remoting.http.servlet.BootstrapListener;
import org.apache.dubbo.remoting.http.servlet.DispatcherServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author bin.li
 * @date 2020/11/11
 */
public class DubboServletConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext.addListener(BootstrapListener.class);
//        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/users/*");
    }
}
