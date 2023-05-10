package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;
import com.alura.jdbc.dao.ProductoDAO;

import java.sql.*;
import java.util.List;

public class ProductoController {

	private final ProductoDAO productoDAO;

	public ProductoController() {
		Connection connection = new ConnectionFactory().recuperaConexion();
		this.productoDAO = new ProductoDAO(connection);
	}

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
		return productoDAO.modificar(nombre, descripcion, cantidad, id);
	}

	public int eliminar(Integer id) {
		return productoDAO.eliminar(id);
	}

	public List<Producto> listar()  {
		return productoDAO.listar();
	}

	public List<Producto> listar(Categoria categoria) {
		return productoDAO.listar(categoria.getId());
	}

    public void guardar(Producto producto, Integer categoriaId) {
		producto.setCategoriaId(categoriaId);
		productoDAO.guardar(producto);
	}
}
