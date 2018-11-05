package com.example.brownshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "couponEntityManagerFactory",
        transactionManagerRef = "couponTransactionManager",
        basePackages = "com.example.brownshop.repository.coupon"
)

public class CouponDbConfig {
    @Autowired
    private Environment env;

    @Bean(name = "couponDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("coupon.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("coupon.datasource.url"));
        dataSource.setUsername(env.getProperty("coupon.datasource.username"));
        dataSource.setPassword(env.getProperty("coupon.datasource.password"));
        return  dataSource;
    }

    @Bean(name = "couponEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean couponEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                             final @Qualifier("couponDataSource") DataSource dataSource) {
        Map<String, String> prop = new HashMap<>();
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        prop.put("hibernate.hbm2ddl.auto", "create-drop");
        return builder
                .dataSource(dataSource)
                .packages("com.example.brownshop.entity.coupon")
                .persistenceUnit("couponDb")
                .properties(prop)
                .build();
    }

    @Bean(name = "couponTransactionManager")
    public PlatformTransactionManager couponTransactionManager(@Qualifier("couponEntityManagerFactory")
                                                                       EntityManagerFactory couponEntityManagerFactory) {
        return new JpaTransactionManager(couponEntityManagerFactory);
    }
}
