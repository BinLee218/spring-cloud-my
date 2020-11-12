package com.company.dubbo.api.service;

import com.company.dubbo.api.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.protocol.rest.support.ContentType;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * @author bin.li
 * @date 2020/11/11
 * 调用：   http://localhost:9090/user
 * 1。企业内部的异构系统之间的（跨语言）调用。Dubbo 的系统做服务提供端，其他语言的系统（也包括某些不基于 Dubbo 的 Java 系统）做服务消费端，两者通过HTTP和文本消息进行通信。
 *    即使相比 Thrift、ProtoBuf 等二进制跨语言调用方案，REST也有自己独特的优势。
 * 2。对外 Open API（开放平台）的开发。既可以用 Dubbo 来开发专门的 Open API 应用，也可以将原内部使用的 Dubbo Service 直接“透明”发布为对外的Open REST API。
 * 3。简化手机（平板）APP 或者 PC 桌面客户端开发。类似于第 2 点，既可以用Dubbo 来开发专门针对无线或者桌面的服务器端，也可以将原内部使用的Dubbo Service 直接”透明“的暴露给手机APP或桌面程序。
 * 4。简化浏览器 AJAX 应用的开发。类似于第 2 点，既可以用 Dubbo 来开发专门的 AJAX 服务器端，也可以将原内部使用的 Dubbo Service 直接”透明“的暴露给浏览器中 JavaScript。
 *    当然，很多 AJAX 应用更适合与 Web 框架协同工作，所以直接访问 Dubbo Service 在很多 Web 项目中未必是一种非常优雅的架构。
 * 5。为企业内部的 Dubbo系统之间提供一种基于文本的、易读的远程调用方式，即服务提供端和消费端都是基于 Dubbo 的系统。
 * 6。一定程度简化 Dubbo 系统对其它异构系统的调用。可以用类似 Dubbo 的简便方式“透明”的调用非 Dubbo 系统提供的 REST 服务（不管服务提供端是在企业内部还是外部）。就是第 1 点的升级版。
 * 链接：https://juejin.im/post/6844904095292063757
 */
@Slf4j
@Path("/")
@DubboService(version = "1.0.0", protocol = { "dubbo", "rest" })
public class RestServiceImpl implements RestService {

    @POST
    @Path(value = "user")
    @Produces({MediaType.APPLICATION_JSON, ContentType.APPLICATION_JSON_UTF_8})
    @Override
    public User getUser() {
        return User.builder().userName("我是REST").passWord("密码REST").build();
    }

    @Override
    @Path("param")
    @GET
    public String param(@QueryParam("param") String param) {
        log.info("/param", param);
        return param;
    }

    @Override
    @Path("params")
    @POST
    public String params(@QueryParam("a") int a, @QueryParam("b") String b) {
        log.info("/params", a + b);
        return a + b;
    }


}

