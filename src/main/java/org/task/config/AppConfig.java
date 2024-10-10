package org.task.config;

import java.sql.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.task.user.UserDao;
import org.task.user.UserService;

@Configuration
public class AppConfig {
    @Bean
    public HikariDataSource getDataSourceConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/java" );
        config.setUsername( "postgres" );
        config.setPassword( "postgres" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        config.addDataSourceProperty("connectionTimeout", "300000");
        return new HikariDataSource(config);
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
