package org.somthing.yellow.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@Data
//@MapperScan(basePackages = "org.graduationdesign.mappers", sqlSessionFactoryRef = "sqlSessionFactory")
public class SpringBootConfig {

    @Value("${qq.mail.transport.protocol}")
    private String protocol;

    @Value("${qq.mail.smtp.port}")
    private String port;

    @Value("${qq.mail.smtp.auth}")
    private String auth;

    @Value("${qq.mail.smtp.host}")
    private String qqHost;

    @Value("${qq.mail.smtp.timeout}")
    private String timeout;

    @Value("${qq.mail.smtp.username}")
    private String qqUsername;

    @Value("${qq.mail.smtp.password}")
    private String qqPassword;

    @Value("${163.mail.smtp.username}")
    private String username163;

    @Value("${163.mail.smtp.password}")
    private String password163;

    @Value("${163.mail.transport.protocol}")
    private String protocol163;

    @Value("${163.mail.smtp.port}")
    private String port163;

    @Value("${163.mail.smtp.auth}")
    private String auth163;

    @Value("${163.mail.smtp.host}")
    private String qqHost163;

    @Value("${163.mail.smtp.timeout}")
    private String timeout163;

    @Value("${mysql.jdbc.url}")
    private String url;

    @Value("${mysql.username}")
    private String username;

    @Value("${mysql.password}")
    private String password;

    @Value("${mysql.driver.class.name}")
    private String driverClassName;

    @Bean("dataSource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }

   /** @Bean("sqlSessionFactory")
    public SqlSessionFactory mySqlSessionFactory() {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        try {
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/Mapper/*Mapper.xml"));
        } catch (IOException e) {
            return null;
        }
        sqlSessionFactoryBean.setTypeAliasesPackage("org.graduationdesign.entity");

        try {
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            return null;
        }
    }*/

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = getRedisConfig();
        JedisPool pool = new JedisPool(config, "localhost", 6379, 0, null);
        return pool;
    }

    @Bean("qqEmailProperties")
    public Properties QQEmail() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.smtp.host", qqHost);
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.timeout", timeout);

        return properties;
    }

    @Bean("qqAccount")
    public Authenticator QQAccount() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(qqUsername, qqPassword);
            }
        };
    }

    @Bean("emailProperties163")
    public Properties Email163() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", protocol163);
        properties.setProperty("mail.smtp.host", qqHost163);
        properties.setProperty("mail.smtp.port", port163);
        properties.setProperty("mail.smtp.auth", auth163);
        properties.setProperty("mail.smtp.timeout", timeout163);
        return properties;
    }

    @Bean("account163")
    public Authenticator Account163() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username163, password163);
            }
        };
    }

}
