package com.stockmaster.migration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.liquibase.url}")
    private String url;
    @Value("${spring.liquibase.user}")
    private String user;
    @Value("${spring.liquibase.password}")
    private String password;

    @Value("${spring.liquibase.driver-class-name}")
    private String driver;

    @Bean
    public DataSource businessDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

}
