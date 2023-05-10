package com.alura.jdbc.pruebas;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.Connection;

public class PruebaPoolDeConexiones {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 20; i++) {
            Connection connection = connectionFactory.recuperaConexion();
            System.out.println("Abriendo la conexión de número " + (i+1) + " : " + connection);
        }
    }
}
