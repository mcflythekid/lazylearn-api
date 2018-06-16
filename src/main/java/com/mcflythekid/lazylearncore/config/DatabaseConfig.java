package com.mcflythekid.lazylearncore.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mcflythekid.lazylearncore.repo")
public class DatabaseConfig {

    @Value("${mysql.server-port}")
    private String mysqlServerPort;

    @Value("${mysql.database}")
    private String mysqlDatabase;

    @Value("${mysql.username}")
    private String mysqlUsername;

    @Value("${mysql.password}")
    private String mysqlPassword;

    private String mysqlJdbcUrl(){
        final String format = "jdbc:mysql://%s/%s?useUnicode=yes&characterEncoding=UTF-8";
        return String.format(format, mysqlServerPort, mysqlDatabase);
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(mysqlJdbcUrl());
        config.setUsername(mysqlUsername);
        config.setPassword(mysqlPassword);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource ds) throws PropertyVetoException{
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(ds);
        entityManagerFactory.setJpaProperties(hibernateProperties());
        entityManagerFactory.setPackagesToScan(new String[]{"com.mcflythekid.lazylearncore.entity"});
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
     //   props.put("hibernate.hbm2ddl.auto", "validate");
        props.put("hibernate.show_sql", false);
        props.put("hibernate.format_sql", true);
        props.put("hibernate.connection.useUnicode", true);
        props.put("hibernate.connection.CharSet", "utf8mb4");
        props.put("hibernate.connection.characterEncoding", "utf8");
        return props;
    }
}