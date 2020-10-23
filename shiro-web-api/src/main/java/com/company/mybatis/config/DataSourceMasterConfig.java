package com.company.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author bin.li
 * @date 2019-03-07
 */

@Configuration
@MapperScan(basePackages = {"com.company.mybatis.dao"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
@Slf4j
public class DataSourceMasterConfig {
    /**
     * mapper.xml文件路径，必须与其他SqlSessionFactory-mapper路径区分.
     */
    public final static String MAPPER_XML_PATH = "classpath*:mapper/*.xml";

    @Primary
    @ConfigurationProperties("spring.datasource.druid.one")
    @Bean
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    //默认Bean首字母小写，简化配置
    //将SqlSessionFactory作为Bean注入到Spring容器中，成为配置一部分。
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceOne());
        /**
         * SpringBootVFS 是类扫描器
         * 打包成jar时，setTypeAliasesPackage(“xxx”)找不到类的问题。MyBatis通过VFS来扫描，
         * 在Spring Boot中由于是嵌套Jar，导致Mybatis默认的VFS实现DefaultVFS无法扫描嵌套Jar中的类，
         * 需要改成SpringBootVFS扫描。
         * 如果是在同一个包下的就不会有这种问题
         */
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH));
        //mybatis.type-handlers-package=
        sqlSessionFactoryBean.setTypeAliasesPackage("com.aaa.core.handler");
        sqlSessionFactoryBean.setTypeHandlersPackage("com.aaa.core.handler");
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        //分页
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect","mysql");
//        properties.setProperty("reasonable","true");
//        properties.setProperty("supportMethodsArguments","true");
//        properties.setProperty("params","count=countSql");
        pageInterceptor.setProperties(properties);
        SqlSessionFactory object = sqlSessionFactoryBean.getObject();
        object.getConfiguration().addInterceptor(pageInterceptor);
        return object;
    }

    @Bean(name = "masterTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceOne());
    }

    @Bean(name = "masterTransactionTemplate")
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager());
    }

    @Bean(name = "masterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

