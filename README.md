# spring cloud 脚手架
## 包含内容有
* Spring cloud eureka server
* Spring cloud eureka provider
* Spring cloud eureka consumer
* Spring cloud hystrix
* Spring cloud zuul
* Rocketmq   
* Redis-Redisson   
### eureka
服务注册与发现  
首先需要启动服务的注册中心，我这里有三个，分别是eureka-server-1、eureka-server-2、eureka-server-3  
然后启动服务提供者，eureka-provider-inner、eureka-provider-outside，这两个名字是为了区分不同类型的服务。  
如果想测试服务的负载均衡，需要将inner与outside的配置文件中的application-name改成一样。  
再启动eureka-consumer，调用controller就可以验证负载均衡了。  
### hystrix
在分布式环境中，许多服务依赖关系中的一些将不可避免地失败。  
Hystrix是一个库，它通过添加延迟容忍和容错逻辑来帮助您控制这些分布式服务之间的交互。  
Hystrix通过隔离服务之间的访问点、停止它们之间的级联故障并提供后备选项来实现这一点，所有这些都提高了系统的整体弹性。  
Hystrix的设计目的如下:
* 保护和控制通过第三方客户机库访问依赖项(通常通过网络)的延迟和故障。
* 在一个复杂的分布式系统中停止一连串的故障。
* 失败快，恢复快。
* 在可能的情况下进行回退并优雅地降级。
* 使接近实时监测，警报，和操作控制。  
首先需要启动服务的注册中心，分别是eureka-server-1、eureka-server-2、eureka-server-3  
随后启动hystrix，再启动consumer进行消费即可，最后postman来调用consumer的controller  
hystrix里面测试了降级（fallbackMethod），超时降级。线程池限流降级等一些基本的功能。  
### Ribbon
Spring Cloud Ribbon是一个客户端负载均衡工具，基于Netflix Ribbon实现。  
通过Spring Cloud的封装，面向服务的REST模版请求自动转换成客户端负载均衡的服务调用。  
Spring Cloud Ribbon 不需要独立部署。    
### Feign
在Ribbon基础上进行封装， 只需创建一个接口并用注解的方式来配置它，即可完成对服务提供方接口的绑定。  
简化了在使用Ribbon的时候，不需要自己封装服务调用客户端。  
### zuul
Zuul是一种网关服务，提供动态路由、监视、弹性、安全性等功能。  
首先需要启动服务的注册中心，分别是eureka-server-1、eureka-server-2、eureka-server-3  
随后启动provider-inner，如果需要负载均衡，需要将inner与outside的配置文件中的application-name改成一样。  
再启动zuul，zuul中有几个filter，pre、post、route、error、一些filter，
还有zuul限流配置，超时的配置等。
### rocketmq
新增了rocketmq消息发送与处理逻辑
### redis-Redisson
新增了redis的Redisson的配置和操作   
操作就是一些基本数据结构操作，还有是分布式锁的调试   
### 增加了mybatis-mysql的多数据源
增加了mybatis-mysql的多数据源   
