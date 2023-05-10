package com.alura.jdbc.pruebas;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaDelete {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().recuperaConexion();
        Statement statement = connection.createStatement();
        String sqlDelete = "DELETE FROM producto WHERE id = " + 99;
        statement.execute(sqlDelete);
        System.out.println(statement.getUpdateCount());
    }
}
