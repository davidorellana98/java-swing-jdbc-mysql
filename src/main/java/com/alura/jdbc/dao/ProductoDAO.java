package com.alura.jdbc.dao;

import com.alura.jdbc.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private final Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public void guardar(Producto producto) {
        try {
            String sqlInsert = "INSERT INTO producto(nombre, descripcion, cantidad, categoria_id) " +
                    "VALUES(?, ?, ?, ?)";

            final PreparedStatement statement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            try(statement) {
                ejecutaRegistro(producto, statement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void ejecutaRegistro(Producto producto, PreparedStatement statement) throws SQLException {
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setInt(3, producto.getCantidad());
        statement.setInt(4, producto.getCategoriaId());
        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();
        try(resultSet) {
            while (resultSet.next()) {
                producto.setId(resultSet.getInt(1));
                System.out.format("Fue insertado el producto %s\n", producto);
            }
        }
    }

    public List<Producto> listar() {
        List<Producto> resultado = new ArrayList<>();

        try {
            String sqlListar = "SELECT id, nombre, descripcion, cantidad FROM producto";
            final PreparedStatement statement = connection.prepareStatement(sqlListar);

            try(statement) {
                boolean result = statement.execute();
                System.out.println(result);
                final ResultSet resultSet = statement.getResultSet();

                try(resultSet) {
                    while (resultSet.next()) {
                        Producto producto = new Producto(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("descripcion"),
                                resultSet.getInt("cantidad")
                        );
                        resultado.add(producto);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int eliminar(Integer id) {
        try {
            String sqlDelete = "DELETE FROM producto WHERE id = ?";
            final PreparedStatement statement = connection.prepareStatement(sqlDelete);

            try(statement) {
                statement.setInt(1, id);
                statement.execute();
                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        try {
            String sqlUpdate = "UPDATE producto SET " +
                    "nombre = ?" +
                    ", descripcion = ?" +
                    ", cantidad = ? " +
                    "WHERE id = ?";
            final PreparedStatement statement = connection.prepareStatement(sqlUpdate);

            try(statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);

                statement.execute();
                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Producto> listar(Integer categoriaId) {
        List<Producto> resultado = new ArrayList<>();

        try {
            String querySelectList = "SELECT id, nombre, descripcion, cantidad FROM producto WHERE categoria_id = ?";
            System.out.println(querySelectList);
            final PreparedStatement statement = connection.prepareStatement(querySelectList);

            try(statement) {
                statement.setInt(1, categoriaId);
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();

                try(resultSet) {
                    while (resultSet.next()) {
                        Producto producto = new Producto(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("descripcion"),
                                resultSet.getInt("cantidad")
                        );
                        resultado.add(producto);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
