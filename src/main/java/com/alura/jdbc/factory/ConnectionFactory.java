package com.alura.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String urlConnection = "jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC";
    private final String userConnection = System.getenv().get("USER_MYSQL");
    private final String passwordConnection = System.getenv().get("PASSWORD_MYSQL");
    private final DataSource dataSource;
    public ConnectionFactory() {
        var poolDataSource = new ComboPooledDataSource();
        poolDataSource.setJdbcUrl(urlConnection);
        poolDataSource.setUser(userConnection);
        poolDataSource.setPassword(passwordConnection);
        poolDataSource.setMaxPoolSize(10); // Limite de conexiones
        this.dataSource = poolDataSource;
    }

    public Connection recuperaConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
