package org.example.DBConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DatabasePool {
    private static HikariDataSource dataSours;
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/glasses");
        config.setPassword("1234321");
        config.setUsername("postgres");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(4);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSours = new HikariDataSource(config);


    }

    public static Connection getConnection() throws SQLException {
        return dataSours.getConnection();
    }

    public static void initDatabase(){
        try(Connection con = getConnection();
            Statement stm = con.createStatement()){
            InputStream is = DatabasePool.class.getResourceAsStream("/init_database.sql");
            if (is == null){
                System.out.println("Что-то пошло не так при загрузке файла /init_database.sql");
                return;
            }
            String sql = new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining("\n"));

            for(String q : sql.split(";")){
                String trim = q.trim();
                if(!trim.isEmpty()){
                    try{
                        stm.execute(trim);
                    }
                    catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
