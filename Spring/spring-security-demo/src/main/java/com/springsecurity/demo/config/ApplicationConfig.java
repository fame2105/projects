package com.springsecurity.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableWebMvc
@ComponentScan("com.springsecurity.demo")
@PropertySource("WEB-INF/persistence-mysql.properties")
public class ApplicationConfig implements WebMvcConfigurer {

    @Autowired
    Environment env;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        comboPooledDataSource.setUser(env.getProperty("jdbc.user"));
        comboPooledDataSource.setPassword(env.getProperty("jdbc.password"));
        comboPooledDataSource.setInitialPoolSize(intValue(env.getProperty("connection.pool.initialPoolSize")));
        comboPooledDataSource.setMinPoolSize(intValue(env.getProperty("connection.pool.minPoolSize")));
        comboPooledDataSource.setMaxPoolSize(intValue(env.getProperty("connection.pool.maxPoolSize")));
        comboPooledDataSource.setMaxIdleTime(intValue(env.getProperty("connection.pool.maxIdleTime")));
        return comboPooledDataSource;
    }

    private int intValue(String str) {
        return Integer.parseInt(str);
    }

}
