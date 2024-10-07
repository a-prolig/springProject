package org.task.dbconnect;

import java.sql.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;


public class DataSource {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource hikariDataSource;

    static {
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/java" );
        config.setUsername( "postgres" );
        config.setPassword( "postgres" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        hikariDataSource = new HikariDataSource( config );
    }
    private DataSource() {}

    @Bean
    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
