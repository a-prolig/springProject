package org.task.config;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.task.user.UserDao;
import org.task.user.UserService;

import javax.sql.DataSource;

@Configuration
public class AppConfig extends HikariConfig{

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/java")
                .username("postgres")
                .password("postgres")
                .build();
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDao();
    }

    @Bean
    public UserService getUserService() {
        return new UserService(getUserDao());
    }
}
