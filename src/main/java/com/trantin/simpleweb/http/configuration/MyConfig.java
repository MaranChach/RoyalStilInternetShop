package com.trantin.simpleweb.http.configuration;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.trantin.simpleweb.http.utils.EmailUtil;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Filter;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resources;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.rmi.server.UID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
@ComponentScan("com.trantin.simpleweb.http")
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
public class MyConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource source = new ComboPooledDataSource();
        try {
            source.setDriverClass("org.postgresql.Driver");
            source.setJdbcUrl("jdbc:postgresql://185.154.195.151:5432/RoyalStil?UseUnicode=true&amp;characterEncoding=utf-8");
            source.setUser("maran");
            source.setPassword("password");
            return source;
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.trantin.simpleweb.http.entity");
        Properties properties = new Properties();
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.setProperty("hibernate.show_sql", "true");
        factoryBean.setHibernateProperties(properties);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        resolver.setPrefix("/WEB-INF/view/");

        return resolver;
    }


    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(100000);
        return resolver;
    }

    @Bean(name = "userIdCookie")
    @Scope("prototype")
    public Cookie userIdCookie(){
        Cookie cookie = new Cookie("USERID", new UID().toString());

        System.out.println(cookie.getValue());

        return cookie;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/sources/**").addResourceLocations("/sources/");
    }

    @Bean(name = "dbconnection")
    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://185.154.195.151:5432/RoyalStil?user=maran&password=password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.yandex.ru");
        mailSender.setPort(465);
        mailSender.setUsername(EmailUtil.fromEmail);
        mailSender.setPassword("jbwqxgemdbpqskwk");
        mailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
        mailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
        return mailSender;
    }*/
}
